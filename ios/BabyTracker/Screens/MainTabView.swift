import SwiftUI

struct MainTabView: View {
    
    
    var body: some View {
        TabView {
            HomeView()
                .tabItem {
                    Label("Home", systemImage: "house")
                }
            
            TrackingView()
                .tabItem {
                    Label("Tracking", systemImage: "map")
                }
            
            InsightsView()
                .tabItem {
                    Label("Insights", systemImage: "chart.bar")
                }
            ProfileView()
                .tabItem {
                    Label("Profile", systemImage: "person")
                }
        }
        .tint(AppColors.primary)
        
        
    }
}

#Preview {
    MainTabView()
}
