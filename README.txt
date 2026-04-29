=========================================
PRÁCTICA #2 - GUÍA DE EJECUCIÓN RÁPIDA
=========================================

1. REQUISITOS:
   - Base de Datos MySQL en la nube (Aiven).
   - Java Spring Boot Framework.

2. CONFIGURACIÓN DE ACCESO:
   - Las credenciales están en: src/main/resources/application.properties
   - Contraseña de BD: Magnus20 //voy a borrarla despues

3. FUNCIONALIDAD IMPLEMENTADA:
   - Se desarrolló una Consulta Ampliada (Query Method) para filtrar videojuegos por rango de precio
   - Ubicación del Controlador: com.gameshop.controller.ConsultaController
   - Ubicación del Repositorio: com.gameshop.repository.GameshopRepository

4. CÓMO PROBAR:
   - Ejecutar el proyecto y navegar a: http://localhost:8081/gameshop/listado
   - Hacer clic en la pestaña "Consulta" del menú superior.

5. Espero implementar la base de datos de mejor manera en el futuro.