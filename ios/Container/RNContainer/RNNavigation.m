//
//  RNNavigation.m
//  Container
//
//  Created by ZHU XUELIANG on 7/4/19.
//  Copyright © 2019 ThoughtWorks. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "RNNavigation.h"
#import "AppDelegate.h"

@implementation RNNavigation

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(popToRoot)
{
    NSLog(@"Pop to root");
    dispatch_async(dispatch_get_main_queue(), ^{
        AppDelegate *appDelegate = (AppDelegate *)UIApplication.sharedApplication.delegate;
        UITabBarController *tabBarController = (UITabBarController *)appDelegate.window.rootViewController;
        UINavigationController *navigationController = (UINavigationController *)tabBarController.selectedViewController;
        [navigationController popViewControllerAnimated:YES];
    });
}

RCT_EXPORT_METHOD(hideTabBar)
{
    NSLog(@"Hide tab bar");
}


@end
