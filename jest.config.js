const {defaults} = require("jest-config");
module.exports = {
  // preset: "react-native",
  moduleFileExtensions: [
    "js"
  ],
  testMatch: [
    "**/__tests__/**/*.[jt]s?(x)",
    "**/?(*.)+(spec|test).[jt]s?(x)",
  ]
};
