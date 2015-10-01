My Karaf Experience
============

### This project contains few sample osgi bundles which are implemented mainly to experience OSGi in Apache Karaf.

## List of examples:

* Activator
 * A bundle activator which is used by other example bundles.
* Command   
 * Few example of karaf command extensions.
* Provider  
 * An OSGi service provider.
* Consumer  
 * A bundle which is using the service from "Provider" bundle.
* Factory   
 * A managed service factory
* Rest      
 * A REST service using Apache CXF, JPA and Hibernate.
* Feature   
 * A karaf feature repository which contains all above bundles and other required dependencies.
* Integration 
 * Example of in container tests using Pax Exam. 
* Distribution 
 * Custom distribution of karaf container with above bundles.
