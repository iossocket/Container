import {createAppContainer, createStackNavigator, createSwitchNavigator} from "react-navigation";
import Home from "./src/Home";
import Page1 from "./src/Page1";
import Page2 from "./src/Page2";

const AppNavigator = createStackNavigator({
  Home: {
    screen: Home
  }
}, {
  initialRouteName: "Home"
});

const PageNavigator = createStackNavigator({
  Page1: {
    screen: Page1
  },
  Page2: {
    screen: Page2
  }
}, {
  initialRouteName: "Page1"
});

export const AppContainer = createAppContainer(AppNavigator);

export const RootContainer = (params) => {
  if (params === "Home") {
    return createAppContainer(AppNavigator);
  } else {
    return createAppContainer(PageNavigator);
  }
}