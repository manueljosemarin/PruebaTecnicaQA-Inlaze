# PruebaTecnicaQA-Inlaze  
Automatización de casos de prueba para la plataforma Inlaze (Registro y Login).  
## Reportes y Errores  
La documentación de los casos de prueba, objetivos y datos de prueba, además de los reportes de bugs encontradoss están disponibles en Notion, junto con capturas de pantalla, los procedimientos de reproducción paso a paso junto con los resultados esperados y encontrados.  
## Requisitos previos  
- **Java 11 o superior**  
- **Apache Maven**  
- **Google Chrome**  
- **ChromeDriver**  
- **Git**  
## Instalación y configuración  
git clone https://github.com/usuario/PruebaTecnicaQA-Inlaze.git  
cd PruebaTecnicaQA-Inlaze  
mvn clean install  
## Ejecución de los scripts  
mvn test  
Para ejecutar un caso específico: 
### Ejemplo:
mvn -Dtest=TC01_RegistroValido test  
## Estructura del Proyecto  
src/  
 └── test/  
     └── java/  
         └── com/  
             └── inlaze/  
                 └── tests/  
                     ├── TC01_RegistroValido.java  
                     ├── TC02_ValidarNombre.java  
                     ├── TC03_FormatoSingEmail.java  
                     ├── TC04_ValidarContraseña.java  
                     ├── TC05_CamposObligatorios.java  
                     ├── TC06_ContraseñasIguales.java  
                     ├── TC07_LoginUsuarioValido.java  
                     ├── TC08_ValidacionLoginIncomple.java  
                     ├── TC09_VerificarNombreUsuario.java  
                     └── TC10_VerificarLogout.java  


