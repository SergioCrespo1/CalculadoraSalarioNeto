# Calculadora de Salario Neto (Android - Jetpack Compose)

Esta aplicación para Android permite calcular de forma sencilla el salario neto a partir del salario bruto anual. Para realizar el cálculo, se tienen en cuenta algunos datos personales, como la edad, los hijos a cargo, el grado de discapacidad y el número de pagas al año.

La aplicación está desarrollada en Kotlin, utilizando Jetpack Compose para crear la interfaz.

Este proyecto se ha realizado como parte de la Actividad 1 de la asignatura Programación Multimedia y Dispositivos Móviles (PMDM).

---

## Características principales

La aplicación cuenta con dos pantallas principales:

- Vista 1 (Formulario de datos):
    - Permite introducir el salario bruto anual, la edad, el número de hijos a cargo y el grado de discapacidad.
    - Ofrece la posibilidad de elegir entre 12 o 14 pagas mediante unos botones de selección.
    - Incluye validaciones para comprobar que los datos introducidos sean correctos y evitar errores durante el cálculo.

- Vista 2 (Resultados):
    - Muestra el salario neto mensual y anual en una tarjeta destacada.
    - Incluye un desglose con el salario bruto anual, la retención de IRPF y la deducción familiar.
    - La información se pasa de una pantalla a otra mediante un Intent, utilizando Serializable para transportar los datos.

- Diseño visual:
    - Se ha buscado una interfaz sencilla, limpia y fácil de utilizar.
    - Los importes se muestran con dos decimales y en euros, facilitando su lectura.

---

## Tecnologías utilizadas

Para desarrollar la aplicación se han utilizado las siguientes herramientas:

- Lenguaje: Kotlin.
- Interfaz: Jetpack Compose con Material Design 3.
- SDK mínimo: API 24 (Android 7.0).
- SDK objetivo: API 34 o superior.
- Entorno de desarrollo: Android Studio.

---

## Estructura del proyecto

El código está organizado en varios archivos, cada uno con una función concreta:

- `CalculadoraSalario.kt`: contiene el modelo de datos (`ResultadoSalario`) y la función encargada de realizar los cálculos (`calcularSalarioNeto`).
- `MainActivity.kt`: controla la primera pantalla, donde se introducen los datos y se realizan las validaciones.
- `ResultadoActivity.kt`: muestra el resultado del cálculo y el desglose de los distintos importes.
- `ui.theme/`: contiene los archivos relacionados con el aspecto visual de la aplicación:
    - `Color.kt`: define la paleta de colores.
    - `Type.kt`: configura la tipografía.
    - `Theme.kt`: establece el tema general de la aplicación.

---

## Instalación y ejecución

Para probar la aplicación, hay que seguir estos pasos:

1. Clonar el repositorio o descargar el código fuente:

   ```bash
   git clone [URL_DEL_REPOSITORIO]
   ```

2. Abrir la carpeta del proyecto desde Android Studio.
3. Sincronizar las dependencias de Gradle mediante la opción Sync Project with Gradle Files.
4. Conectar un dispositivo Android con la depuración USB activada o iniciar un emulador.
5. Pulsar el botón Run (`Shift + F10`) para compilar y ejecutar la aplicación.

---

## Autor

- Nombre: Sergio Crespo
- Módulo: Programación Multimedia y Dispositivos Móviles (DAM)