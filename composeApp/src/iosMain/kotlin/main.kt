import androidx.compose.ui.window.ComposeUIViewController
import org.ncgroup.kmap.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
