import React, {Component} from 'react';
import {StyleSheet, View} from 'react-native';
import {RootContainer} from "./Router";

export default class App extends Component {
  render() {
    const Router = RootContainer("Page");
    return (
      <View style={styles.container}>
        <Router detached={true} onNavigationStateChange={(prevState, nextState, action) => {
          // console.log(prevState);
          // console.log(nextState);
          console.log(action);
        }}/>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  }
});
