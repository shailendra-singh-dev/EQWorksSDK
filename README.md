# EQWorksSDK

# EQWorksSDK
1. SDK Package
Implement a log() function to POST geolocation information to a designated server and package it as a robust SDK for import and use.
Support multiple geolocation precision levels based on the given application's permission scope; use the most refined precision available — worst-case scenario when the application configures no geolocation permission scopes, fall back to (0, 0) coordinate.
Take into account the developer experience of using the package to be as simple as possible. For example, provide the current time if the application developer opts not to specify an explicit timestamp when calling the log() function.
Tend to the end-user experience so that the SDK package would not result in significant performance degradation of the application and provide a discreet way to log back to the server-side when errors occur.
Try to make the SDK as universally applicable as possible, but prioritize mobile devices if there are significant differences in allowed scopes to capture geolocation signals.

2. Design and Implement Tests
Based on the SDK Package implementation, design and implement:

Mocked (emulated HTTP activities) unit tests.
End-to-end tests against a server of your choice (such as httpbin).

