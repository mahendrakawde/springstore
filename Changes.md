# Changes with regard to the starting project #

Initially it was decided that we where going to take the project as is, however this project was hard to import into a IDE (we used Eclipse). Therefor it was decided to make some modifications to the deployment and build of the project.

### Maven instead of Ant ###
Instead of using the ant based build system we ported the build and deployment system to [Maven](http://maven.apache.org). This gives us the advantage of dependency management and we could use the different plugins for generation of some nice reporting.

### Deployment ###
Deployment is now also maven based. Deployment changed in this regard that the only artifact that is rebuild is the OPC and Consumer Website all the other artifacts are treated as external application. (Which in a real situation would also be the case).

### Code Generation ###
In the original ant based build for each build the java files and xml and wsdl files where regenerated. In a real situation you should generate once (preferably not at all), we decided to generate the classes and files once and include them in the build. They can be found in the src/generated directory. They are included in the different jar/ear files when build.

### Reporting ###
The initial project didn't have any reporting on bugs, code smells, test reports (or tests) for that matter. As we wanted to show the improvement in quality and reduction of complexity we decided to add reporting to the project. However as we only focus on the code we would have in control only the Components, OPC and Consumer Website artifacts have extensive reporting enabled. For all the other projects it is limited to the default/basic reporting that maven delivers.