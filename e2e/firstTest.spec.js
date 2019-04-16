function sleep(ms){
  return new Promise(resolve=>{
    setTimeout(resolve,ms);
  });
}

describe('Example', () => {
  beforeEach(async () => {
    // await device.reloadReactNative();
  });

  it('detox example', async () => {
    console.log('Begin $$$$$');
    const button = await element(by.type('android.widget.Button'));
    await expect(button).toBeVisible();
    
    console.log("===>", button);
    await button.tap();
    // done();
    // process.nextTick(async () => {
    //   const backButton = await element(by.id('back-button'));
    //   await expect(backButton).toBeVisible();
    //   done();
    // });
    
    // await backButton.tap();
    // await expect(button).toBeVisible();
  });
}); 


