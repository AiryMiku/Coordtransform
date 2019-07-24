# Coordtransform
[ ![Download](https://api.bintray.com/packages/airy/Coordtransform/AFCoordtransform/images/download.svg?version=0.0.1) ](https://bintray.com/airy/Coordtransform/AFCoordtransform/0.0.1/link)

# Thank For
[https://github.com/wandergis/coordTransform](https://github.com/wandergis/coordTransform)

## Getting Started
#### Step 1.Add it in your root build.gradle at the end of repositories and add the dependency:
use maven
```
<dependency>
	<groupId>com.airy.AFcoordtransform</groupId>
	<artifactId>AFCoordtransform</artifactId>
	<version>0.0.1</version>
	<type>pom</type>
</dependency>
```

use gradle
```
    allprojects {
		repositories {
			...
			jcenter()
		}
	}
```

```
    compile 'com.airy.AFcoordtransform:AFCoordtransform:0.0.1'
```

use ivy
```
<dependency>
	<groupId>com.airy.AFcoordtransform</groupId>
	<artifactId>AFCoordtransform</artifactId>
	<version>0.0.1</version>
	<type>pom</type>
</dependency>
```

#### Step 2.Use in your code
```java
    double latlng[] = Coordtransform.gcj02tobd09(latitude, longitude);
    double lat = latlng[0];
    double lng = latlng[1];
```