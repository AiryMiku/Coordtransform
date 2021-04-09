# Coordtransform
[ ![Download](https://jitpack.io/v/AiryMiku/Coordtransform.svg) ](https://jitpack.io/#AiryMiku/Coordtransform/0.0.2)

# Thank
[https://github.com/wandergis/coordTransform](https://github.com/wandergis/coordTransform)

## Getting Started
#### Step 1.Add it in your root build.gradle at the end of repositories and add the dependency:
use maven
```
// use jitpack
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
	<groupId>com.airy.AFcoordtransform</groupId>
	<artifactId>AFCoordtransform</artifactId>
	<version>0.0.2</version>
	<type>pom</type>
</dependency>
```

use gradle
```
    allprojects {
		repositories {
			...
			// better use this
			maven { url 'https://jitpack.io' }	
			// jcenter will be end of service at February 1st 2022
			jcenter()	
		}
	}
```

```
    compile 'com.airy.AFcoordtransform:AFCoordtransform:0.0.2'
	// or
	implementation 'com.airy.AFcoordtransform:AFCoordtransform:0.0.2'
```

use ivy
```
<dependency>
	<groupId>com.airy.AFcoordtransform</groupId>
	<artifactId>AFCoordtransform</artifactId>
	<version>0.0.2</version>
	<type>pom</type>
</dependency>
```

#### Step 2.Use in your code
```java
    double latlng[] = Coordtransform.gcj02tobd09(latitude, longitude);
    double lat = latlng[0];
    double lng = latlng[1];
```