# Container

This a hybrid app (native iOS, native Android, react native & flutter) to demo
1. Possibility of mixed use of these mobile development technologies
2. Integrated UI test via Detox or Appium

## Conclusions

Hybriding these techs into a single app is possible. 

### Integrated UI tests

[Detox](https://github.com/wix/Detox) declares to support either react native or pure native apps, but not hybird ones. 

If we use native view as the first rendering view instead of react native, detox will end up in a loop waiting for 
react native to be loaded, which would not occur until the react native view is rendered in the app. This results in a timeout failure. 
The related code can be found at the [entrypoint](https://github.com/wix/Detox/blob/38644af6809a0bde7f130fe5deda9a0c339423e6/detox/android/detox/src/main/java/com/wix/detox/DetoxManager.java#L64)
of detox starting, in which there is a [function](https://github.com/wix/Detox/blob/da5a75f7a719fac240b7ffe4b391776e801c360f/detox/android/detox/src/main/java/com/wix/detox/ReactNativeCompat.java#L31) waiting for 
react native loading. 

The demo code on detox is found in the `master` branch. 

#### Appium

We followed the [Getting started](https://github.com/appium/appium/blob/master/docs/en/about-appium/getting-started.md) doc of Appium 
to set it up and it works nicely for hybird apps, since it takes the black-box testing strategy. 

1. We used the [WebDriverIO](https://webdriver.io/) as an client to talk to the Appium server, which can be replaced with any other
REST client with better efficiency or interface, e.g. [Postmant Newman](https://learning.getpostman.com/docs/postman/collection_runs/command_line_integration_with_newman/)

2. To unify element finding and selecting operation, an `accessibility id` attribute can be supplied. Details to be found via this [post](https://developers.perfectomobile.com/display/TT/React-Native+and+unique+identifiers).

The demo code on Appium is found in the `appium` branch. 
