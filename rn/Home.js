import React, {Component} from 'react';
import {StyleSheet, View} from 'react-native';
import {RootContainer} from "./Router";

export default class Home extends Component {
  render() {
    const Router = RootContainer("Home");
    return (
      <View style={styles.container}>
        <Router detached={true} />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  }
});
