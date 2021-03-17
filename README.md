# EQWorksSDK
1. SDK Package
a.Implemented log() function to POST geolocation information to a designated server and package it as a robust SDK for import and use.
b.It supports multiple geolocation precision levels based on the given application's permission scope; use the most refined precision available — worst-case scenario when the application configures no geolocation permission scopes, fall back to (0, 0) coordinate.
c.It took into account the developer experience of using the package to be as simple as possible. For example, provide the current time if the application developer opts not to specify an explicit timestamp when calling the log() function.
d.It tends to the end-user experience so that the SDK package would not result in significant performance degradation of the application and provide a discreet way to log back to the server-side when errors occur.

2. Design and Implement Tests
a.Mocking of class (emulated HTTP activities) unit tests.
b.End-to-end tests against a server of choice (such as httpbin).

