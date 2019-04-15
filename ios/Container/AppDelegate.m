//
//  AppDelegate.m
//  Container
//
//  Created by ZHU XUELIANG on 6/4/19.
//  Copyright © 2019 ThoughtWorks. All rights reserved.
//

#import "AppDelegate.h"
#import "DashboardViewController.h"
#import "DietViewController.h"
#import "HistoryViewController.h"
#import "RNContainer/RNContrainer.h"

@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    
    self.flutterEngine = [[FlutterEngine alloc] initWithName:@"io.flutter" project:nil];
    [self.flutterEngine runWithEntrypoint:nil];
    
    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    UITabBarController *tabBarController = [[UITabBarController alloc] init];

    tabBarController.viewControllers = [self tabBarViewControllers];

    self.window.rootViewController = tabBarController;
    [self.window makeKeyAndVisible];
    return YES;
}

- (NSArray<UIViewController *> *)tabBarViewControllers {
    UIViewController *dashboardViewController = [DashboardViewController viewController];
    dashboardViewController.tabBarItem = [[UITabBarItem alloc] initWithTabBarSystemItem:UITabBarSystemItemBookmarks tag:0];
    UIViewController *challengeViewController = [[RNContrainer new] viewControllerByRoute:@"Home"];
    challengeViewController.tabBarItem = [[UITabBarItem alloc] initWithTabBarSystemItem:UITabBarSystemItemFeatured tag:1];
    UIViewController *dietViewController = [DietViewController viewController];
    dietViewController.tabBarItem = [[UITabBarItem alloc] initWithTabBarSystemItem:UITabBarSystemItemSearch tag:2];
    FlutterViewController *historyViewController = [[FlutterViewController alloc] initWithEngine:self.flutterEngine nibName:nil bundle:nil];
    historyViewController.tabBarItem = [[UITabBarItem alloc] initWithTabBarSystemItem:UITabBarSystemItemHistory tag:3];
    return @[dashboardViewController, challengeViewController, dietViewController, historyViewController];
}


@end
