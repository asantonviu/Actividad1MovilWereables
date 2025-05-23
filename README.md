<span>
<p align="center"><a href="https://www.universidadviu.com/es/" target="_blank"><img src="https://upload.wikimedia.org/wikipedia/commons/f/f8/Logo_VIU.png" width="400" alt="VIU Logo"></a></p>

<p align="center"><a href="https://www.android.com" target="_blank"><img src="https://upload.wikimedia.org/wikipedia/commons/3/3e/Android_logo_2023.svg" width="400" alt="Android Logo"></a></p>

<p align="center"><a href="https://kotlinlang.org/" target="_blank"><img src="https://developer.android.com/static/codelabs/basic-android-kotlin-compose-first-program/img/840cee8b164c10b_1920.png?hl=es-419" width="400" alt="Kotlin Logo"></a></p>


</span>

## Developers

  - Antonio Sánchez Antón
  - Febe Rubén Fariña Aguirre

Estudiantes del Máster Universitario en Desarrollo de Aplicaciones y Servicios Web de la Universidad Internacional de Valencia (VIU).  
Forma parte de la asignatura de Programación Dispositivos Moviles (Wereables) impartida por el profesor Francisco Gomez Arnal, en concreto su Actividad 1. 

## Description  
Esta aplicación nace con el objetivo de ofrecer a los usuarios una forma rápida, intuitiva y precisa de consultar el clima actual y el histórico en su ubicación o en cualquier ciudad de interés.  
La motivación principal es facilitar la toma de decisiones cotidianas (como planificar viajes, actividades al aire libre o gestión de recursos) mediante información meteorológica fiable, accesible desde cualquier dispositivo móvil.  
Los objetivos principales son:  
  - Proporcionar información meteorológica en tiempo real basada en la localización del usuario.
  - Permitir la consulta del historial climático seleccionando fechas pasadas.
  - Ofrecer una experiencia de usuario sencilla y fluida, con navegación intuitiva y actualización automática de los datos.
  - Utilizar las mejores prácticas de desarrollo móvil moderno (Jetpack Compose, arquitectura MVVM, inyección de dependencias con Hilt).
  - Optimizar el consumo de recursos del dispositivo, minimizando llamadas innecesarias a la API y reutilizando datos mediante un ModelViewViewModel compartido.


Para ello la aplicación dispone de 4 pantallas:
  - Pantalla de Splash, muestra un icono.
  - Metereologia actual, es la pantalla por defecto y muestra la metereologia para la ubicacion actual del dispositivo.
    Se permite introducir otra localidad.
  - Prevision metereologica a 7 dias para la ubicación actual.
  - Metereologia a una fecha pasada.

La aplicacion hace uso de la ultima ubicacion del dispositivo por lo que, al arrancar, solicita los permisos necesarios.  
Utilizamos el API de OpenWeather para obtener los datos.  
Utilizamos el API de GeoCoder de Google para saber que localidad se corresponde a que coordenadas y viceversa.


#### Imagenes
   <img src="https://github.com/user-attachments/assets/2d47a747-2b5c-4395-919d-8c62a7059e2d" />  
   <img src="https://github.com/user-attachments/assets/cc6e7f22-a717-4d58-9a1c-0c3c982c9d8a" />  
   <img src="https://github.com/user-attachments/assets/2a27d908-479a-47cf-b51c-32880ec50e79" />
   <img src="https://github.com/user-attachments/assets/6cd4c344-5e3e-4605-8e81-7b2d9e4d8c1d" /> 
   <img src="https://github.com/user-attachments/assets/7c662ab9-7b7e-4ade-80d2-8d32b7255e2d" /> 
   <img src="https://github.com/user-attachments/assets/b3ae2464-f140-4e09-8721-a28877e6f7ba" /> 
   <img src="https://github.com/user-attachments/assets/b3ae2464-f140-4e09-8721-a28877e6f7ba" /> 
