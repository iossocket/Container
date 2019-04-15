//
//  RNContrainer.m
//  Container
//
//  Created by ZHU XUELIANG on 6/4/19.
//  Copyright © 2019 ThoughtWorks. All rights reserved.
//

#import "RNContrainer.h"
#import "RNBridge.h"
#import <React/RCTRootView.h>

@interface RNViewController : UIViewController

@end

@implementation RNViewController

-(void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:YES animated:animated];
}

- (void) viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:animated];
}

@end

@implementation RNContrainer

- (UIViewController *)viewControllerByRoute:(NSString *)routeName {
    UIViewController *viewController = [[RNViewController alloc] init];
    RCTRootView *rootView = [[RCTRootView alloc] initWithBridge:[RNBridge sharedBridge].bridge
                                                     moduleName:routeName
                                              initialProperties:nil];
    viewController.view = rootView;
    viewController.hidesBottomBarWhenPushed = YES;
    return viewController;
}

@end
