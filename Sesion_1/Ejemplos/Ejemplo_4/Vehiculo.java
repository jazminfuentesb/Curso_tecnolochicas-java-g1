package Ejemplo_04;

// Clase base sellada: solo las clases permitidas pueden extenderla.
// Mejora el control de herencia en jerarquías cerradas.
public sealed class Vehiculo permits Auto, Camion { }

// Clase Auto que extiende Vehiculo. Se declara como final para no permitir más herencia.
final class Auto extends Vehiculo { }

// Clase Camion que también extiende Vehiculo y es final.
final class Camion extends Vehiculo { }

// Esta clase NO está en la lista de 'permits', por lo tanto, genera un error.
//class Moto extends Vehiculo { }

// Comenta o Descomenta la clase solo para mostrar el error.
// Error: 'Moto' is not allowed in the sealed hierarchy