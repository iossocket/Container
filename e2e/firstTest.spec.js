describe('Example', () => {
  beforeEach(async () => {
    // await device.reloadReactNative();
  });

  it('detox example', async () => {
    const button = element(by.text('Show RN Page'));
    await expect(button).toBeVisible();

    await button.tap();
    const backButton = element(by.id('back-button'));
    await expect(backButton).toBeVisible();
    await backButton.tap();
    await expect(button).toBeVisible();
  });
}); 