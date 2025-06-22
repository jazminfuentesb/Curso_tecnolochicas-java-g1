package com.example.ucimonitoring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.Random;

@SpringBootApplication
public class UciMonitoringApplication implements CommandLineRunner {

    private static final Random random = new Random();

    // Clase para representar los signos vitales de un paciente
    static class VitalSigns {
        int patientId;
        int heartRate;
        String bloodPressure; // Usamos String para la PA (ej. "120/80")
        int spo2;

        public VitalSigns(int patientId, int heartRate, String bloodPressure, int spo2) {
            this.patientId = patientId;
            this.heartRate = heartRate;
            this.bloodPressure = bloodPressure;
            this.spo2 = spo2;
        }

        @Override
        public String toString() {
            return "Paciente " + patientId + ": FC=" + heartRate + " bpm, PA=" + bloodPressure + " mmHg, SpO2=" + spo2 + "%";
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(UciMonitoringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Generar flujos para 3 pacientes
        Flux<VitalSigns> patient1Data = generatePatientData(1);
        Flux<VitalSigns> patient2Data = generatePatientData(2);
        Flux<VitalSigns> patient3Data = generatePatientData(3);

        // Fusionar los flujos de los 3 pacientes en un solo flujo
        Flux<VitalSigns> mergedPatientsData = Flux.merge(patient1Data, patient2Data, patient3Data);

        System.out.println("Iniciando monitoreo de signos vitales en UCI con Spring Boot...");
        System.out.println("---------------------------------------------------------------");

        // Procesar el flujo de datos fusionado
        mergedPatientsData
            .filter(UciMonitoringApplication::isCritical) // Filtrar solo los eventos críticos
            .delayElements(Duration.ofSeconds(1)) // Aplicar backpressure: procesar eventos críticos más lento
            .doOnNext(UciMonitoringApplication::showAlert) // Mostrar alerta para eventos críticos
            .subscribe(); // Iniciar la suscripción para que el flujo comience a emitir

        // Mantener la aplicación en ejecución para que los flujos continúen
        // En un caso real con WebFlux, un endpoint mantendría la app viva.
        // Para esta simulación, podemos mantener el hilo principal un poco más.
        Thread.sleep(60000); // Ejecutar por 60 segundos para ver las alertas
        System.out.println("Simulación finalizada.");
    }

    // Método para generar datos aleatorios de signos vitales para un paciente
    private static Flux<VitalSigns> generatePatientData(int patientId) {
        return Flux.interval(Duration.ofMillis(300)) // Emitir eventos cada 300 ms
            .map(tick -> {
                int heartRate = 40 + random.nextInt(100); // FC entre 40 y 140
                int systolicBP = 80 + random.nextInt(80); // PA Sistólica entre 80 y 160
                int diastolicBP = 50 + random.nextInt(50); // PA Diastólica entre 50 y 100
                int spo2 = 80 + random.nextInt(20); // SpO2 entre 80 y 100
                return new VitalSigns(patientId, heartRate, systolicBP + "/" + diastolicBP, spo2);
            })
            .doOnNext(data -> System.out.println("Generando " + data)); // Opcional: para ver todos los datos generados
    }

    // Método para verificar si un signo vital es crítico
    private static boolean isCritical(VitalSigns vs) {
        // Frecuencia cardíaca
        if (vs.heartRate < 50 || vs.heartRate > 120) {
            return true;
        }

        // Presión arterial
        String[] bpParts = vs.bloodPressure.split("/");
        if (bpParts.length == 2) {
            try {
                int systolic = Integer.parseInt(bpParts[0]);
                int diastolic = Integer.parseInt(bpParts[1]);
                if ((systolic < 90 || diastolic < 60) || (systolic > 140 || diastolic > 90)) {
                    return true;
                }
            } catch (NumberFormatException e) {
                // Manejar error si el formato de la PA es incorrecto
                System.err.println("Error al parsear presión arterial: " + vs.bloodPressure);
            }
        }

        // Oxígeno en sangre
        if (vs.spo2 < 90) {
            return true;
        }

        return false;
    }

    // Método para mostrar la alerta en consola
    private static void showAlert(VitalSigns vs) {
        String alertMessage = "";
        // Priorizar FC sobre PA o SpO2
        if (vs.heartRate < 50) {
            alertMessage = "⚠️ Paciente " + vs.patientId + " - FC crítica: " + vs.heartRate + " bpm (muy baja)";
        } else if (vs.heartRate > 120) {
            alertMessage = "⚠️ Paciente " + vs.patientId + " - FC crítica: " + vs.heartRate + " bpm (muy alta)";
        } else {
            String[] bpParts = vs.bloodPressure.split("/");
            if (bpParts.length == 2) {
                int systolic = Integer.parseInt(bpParts[0]);
                int diastolic = Integer.parseInt(bpParts[1]);
                if (systolic < 90 || diastolic < 60) {
                    alertMessage = "⚠️ Paciente " + vs.patientId + " - PA crítica: " + vs.bloodPressure + " mmHg (baja)";
                } else if (systolic > 140 || diastolic > 90) {
                    alertMessage = "⚠️ Paciente " + vs.patientId + " - PA crítica: " + vs.bloodPressure + " mmHg (alta)";
                }
            }
            if (alertMessage.isEmpty() && vs.spo2 < 90) {
                alertMessage = "⚠️ Paciente " + vs.patientId + " - SpO2 baja: " + vs.spo2 + "%";
            }
        }

        if (!alertMessage.isEmpty()) {
            System.out.println(alertMessage);
        }
    }
}