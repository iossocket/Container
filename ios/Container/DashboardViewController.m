//
// Created by ZHU XUELIANG on 2019-04-06.
// Copyright (c) 2019 ThoughtWorks. All rights reserved.
//

#import "DashboardViewController.h"
#import "RNContrainer.h"

@interface DashboardViewController ()

@property (nonatomic, strong) RNContrainer *container;

@end

@implementation DashboardViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = @"Bookmarks";
    UIButton *button = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, 120, 40)];
    [button setTitle:@"Show RN Page" forState:UIControlStateNormal];
    [button setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [self.view addSubview:button];
    button.center = self.view.center;
    
    [button addTarget:self action:@selector(showRN) forControlEvents:UIControlEventTouchUpInside];
}

- (void)showRN {
    UIViewController *vc = [self.container viewControllerByRoute:@"App"];
    [self showViewController:vc sender:nil];
    
}

- (RNContrainer *)container {
    if (!_container) {
        _container = [[RNContrainer alloc] init];
    }
    return _container;
}

@end
