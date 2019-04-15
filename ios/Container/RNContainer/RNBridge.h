//
//  RNBridge.h
//  Container
//
//  Created by ZHU XUELIANG on 7/4/19.
//  Copyright © 2019 ThoughtWorks. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridge.h>

NS_ASSUME_NONNULL_BEGIN

@interface RNBridge : NSObject

@property (nonatomic, strong, readonly) RCTBridge *bridge;

+ (instancetype _Nonnull)sharedBridge;

@end

NS_ASSUME_NONNULL_END
