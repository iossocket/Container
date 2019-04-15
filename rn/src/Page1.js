import React, {Component} from "react";
import {View, Text, Button, TouchableOpacity} from "react-native";
import {RNNavigation} from "../NativeNavigation";

export default class Page1 extends Component {
  static navigationOptions = {
    title: "RN Page",
    headerLeft: (
      <TouchableOpacity 
        style={{marginLeft: 12}}
        onPress={() => {
          RNNavigation.popToRoot();
        }}>
        <Text>Back</Text>
      </TouchableOpacity>
    )
  };

  render() {
    return (
      <View style={{flex: 1, justifyContent: "center", alignItems: "center"}}>
        <Text>This is page1 page</Text>
        <Button
          title="Go to next page"
          onPress={() => this.props.navigation.navigate('Page2')}
        />
      </View>
    )
  }
}