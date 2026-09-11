//
//  InitialOnboardingBannerView.swift
//  BabyTracker
//
//  Created by Ismael Costa on 09/09/26.
//

import SwiftUI

struct InitialOnboardingBannerView: View {
    let image: String
    
    var body: some View {
        ZStack{
            
            Image(image)
                .resizable()
                .scaledToFit()
                .frame(width: 280, height: 280)
                
        }
    }
}

#Preview {
    InitialOnboardingBannerView(image: "mommyBaby")
}

