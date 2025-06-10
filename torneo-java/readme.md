Paso 5: Compilar y Ejecutar la Simulación

Asegúrate de que todos los archivos .java estén en la estructura de directorios correcta (dentro de una carpeta src).
Compila todos los archivos Java. Desde la raíz de tu proyecto (la carpeta que contiene src), puedes usar:
javac -d out src/com/torneotenismesa/**/*.java
(Esto compilará los archivos y los dejará en una carpeta out manteniendo la estructura de paquetes).
Ejecuta la clase principal:
java -cp out com.torneotenismesa.simulacionintegrada.SimuladorIntegradoJava