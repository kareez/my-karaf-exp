My Karaf Experience
============

This project contains few sample osgi bundles which are implemented mainly to experience osgi in Apache Karaf.

Here is the list of bundles:
- Activator: A sample bundle activator which is used by other bundles in the project.
- Command:   A sample osgi command extension.
- Provider:  A sample service which is published by blueprint.
- Consumer:  A sample consumer bundle which is using the service that is provided by "Provider" bundle.
- Rest:      A sample rest service which is using Apache CXF and publishied by blueprint.
- Feature:   A sample Apache Karfa feature repository which contains all above bundle.