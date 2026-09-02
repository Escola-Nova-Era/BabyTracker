import SwiftUI
import SwiftData

@main
struct BabyTrackerApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
        .modelContainer(for: [TrackingEvent.self, Baby.self])
    }
}
