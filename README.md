# Wompi Automation

Este proyecto implementa un framework de automatización de pruebas para la plataforma **Wompi**, incluyendo pruebas de UI y de integración de API.

## Arquitectura

El framework está basado en **Screenplay Pattern**, utilizando **Java** y **Selenium / WebDriverIO** para la automatización de UI, y pruebas de integración de API para validar transacciones y flujos clave.

**Estructura del proyecto:**
wompi-automation/
├─ src/
│ ├─ main/
│ └─ test/
│ ├─ java/
│ │ ├─ tasks/ # Acciones o tareas que el actor realiza
│ │ ├─ questions/ # Validaciones y consultas al sistema
│ │ └─ stepdefinitions/ # Definición de los escenarios de prueba
│ └─ resources/
│ └─ features/ # Archivos .feature de Cucumber
├─ pom.xml # Dependencias y configuración de Maven
└─ target/ # Reportes de ejecución


**Diseño de los escenarios de prueba:**  
- **UI Tests**: Automatización de flujos en la web de Wompi.  
- **API Integration Tests**: Validación de endpoints críticos según los escenarios del proyecto.

## Tecnologías y Herramientas

- Java 17  
- Selenium / WebDriverIO  
- Cucumber  
- Maven  
- Git / GitHub  

## Instalación

1. Clonar el repositorio:  
```bash
git clone https://github.com/Maquiniando/Wompi-Automation.git


Ingresar al proyecto:

cd wompi-automation


Instalar dependencias con Maven:

mvn clean install

Ejecución de pruebas

Ejecutar todos los tests:

mvn test


Ejecutar un escenario específico de Cucumber:

mvn test -Dcucumber.options="--tags @NombreDelTag"

Ejecutar el script test integration Api
