//
//  ContentView.swift
//  BabyTracker
//
//  Created by Marcos Vinícius Vieira on 18/04/26.
//

import SwiftUI

struct ContentView: View {
    @StateObject private var viewModel = AuthenticationViewModel()

    var body: some View {
        CreateAccountView(
            viewModel: viewModel,
            onSignInTap: { }
        )
    }
}

#Preview {
    ContentView()
}

