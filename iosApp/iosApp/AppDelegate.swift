//
//  AppDelegate.swift
//  iosApp
//
//  Created by Ismail Mohamed on 10/02/2024.
//

import Foundation
import UIKit
import GoogleMaps

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        GMSServices.provideAPIKey("changeme")
        return true
    }
}
