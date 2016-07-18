<h1> PCF Telstra WIFI API Consumer </h1>

The following project can be used to consume the Telstra WIFI Api from a spring boot application. It presents a simple Google Map
which will drill into your current location to allow you to find WIFI HotSppts 

The application can be deployed to Pivotal Web Services (PWS) using a manifest.yml file as follows

```
applications:
- name: pas-telstrawifiapi-client
  memory: 512M
  instances: 1
  host: pas-telstrawifi
  path: ./target/TelstraWIFIAPIPublic-0.0.1-SNAPSHOT.jar
```

It can be accessed from a deployed instance on PWS using the link below.

```
http://pas-telstrawifi.cfapps.io/
```

Finally it will run a mobile devices as well BUT you need to turn on Location based services for your browser to ensure it 
can share the current location with Google MAP 

![alt tag](https://dl.dropboxusercontent.com/u/15829935/platform-demos/images/piv-telstra-wfi1.png)

<hr />
Pas Apicella [papicella at pivotal.io] is a Senior Platform Architect at Pivotal
