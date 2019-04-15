//
//  RNBridgeDelegate.m
//  Container
//
//  Created by ZHU XUELIANG on 7/4/19.
//  Copyright © 2019 ThoughtWorks. All rights reserved.
//

#import "RNBridgeDelegate.h"
#import <React/RCTBundleURLProvider.h>

@implementation RNBridgeDelegate

- (NSURL *)sourceURLForBridge:(RCTBridge *)bridge
{
#if DEBUG
    return [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index" fallbackResource:nil];
#else
    return [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];
#endif
}

@end
