//
// Created by ZHU XUELIANG on 2019-04-06.
// Copyright (c) 2019 ThoughtWorks. All rights reserved.
//

#import "BaseViewController.h"


@implementation BaseViewController

+ (UIViewController *)viewController {
    UIViewController *vc = (UIViewController *)[[self.class alloc] init];
    return [[UINavigationController alloc] initWithRootViewController:vc];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
}

@end