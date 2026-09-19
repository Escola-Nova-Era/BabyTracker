//
//  OnboardingView.swift
//  BabyTracker
//
//  Created by Ismael Costa on 03/09/26.
//

import SwiftUI

struct OnboardingView: View {
    @State private var viewModel = OnboardingViewModel();
    
    var body: some View {
        TabView(selection: $viewModel.currentPage){
            ForEach(Array(viewModel.pages.enumerated()), id: \.element.id) { index, page in
                
                OnboardingPageView(page: page, pageIndex:  index, currentPage: viewModel.currentPage,numberOfPages: viewModel.pages.count).tag(index)
                
            }
        }.tabViewStyle(.page(indexDisplayMode: .never))
            .ignoresSafeArea()
    }
}

#Preview {
    OnboardingView()
}
