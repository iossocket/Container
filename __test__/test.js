const wdio = require("webdriverio");

// const opts = {
//   port: 4723,
//   capabilities: {
//     platformName: "Android",
//     platformVersion: "9",
//     deviceName: "Nexus5X",
//     app: "/Users/xueliang.zhu/Documents/ios/Container/android/app/build/outputs/apk/debug/app-debug.apk",
//     automationName: "UiAutomator2"
//   }
// };

const opts = {
  port: 4723,
  capabilities: {
    platformName: 'iOS',
    automationName: 'XCUITest',
    deviceName: 'iPhone X',
    platformVersion: '12.2',
    app: 'ios/build/Build/Products/Debug-iphonesimulator/Container.app'
  }
};

jest.setTimeout(30000);

function sleep(ms){
  return new Promise(resolve=>{
    setTimeout(resolve,ms)
  })
}

describe('test', () => {
  let client;
  beforeAll(async () => {
    client = await wdio.remote(opts);
  })
  it('should work', async () => {
    const button = await client.findElement("accessibility id", "show");
    expect(button).not.toBeNull();
    await client.elementClick(button.ELEMENT);
    await sleep(5000);
    const backButton = await client.findElement("accessibility id", "back-button");
    expect(backButton).not.toBeNull();
  })
})

