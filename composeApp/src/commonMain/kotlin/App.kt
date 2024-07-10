import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import navigation.ModalBottomSheetLayout
import navigation.bottomSheet
import navigation.rememberBottomSheetNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import screen.bottomSheetFullSize.BSFullScreenLayout
import screen.bottomSheetWithClose.BSWithCloseLayout
import screen.bottomSheetWithParameters.BSWithParametersLayout


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

        val bottomSheetNavigator =
            rememberBottomSheetNavigator(skipPartiallyExpanded = true)
        val navController = rememberNavController(bottomSheetNavigator)

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            ModalBottomSheetLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(12.dp),
                bottomSheetNavigator = bottomSheetNavigator
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home
                ) {
                    composable<Screen.Home> {
                        Column {
                            Text(
                                text = "Bottom sheet navigation examples",
                                style = MaterialTheme.typography.headlineMedium,
                                textAlign = TextAlign.Center,
                            )
                            Spacer(modifier = Modifier.size(12.dp))

                            Button(onClick = { navController.navigate(Screen.BottomSheetFullScreen) }) {
                                Text(text = "BottomSheetFullScreen")
                            }

                            Button(onClick = { navController.navigate(Screen.BottomSheetWithCloseScreen) }) {
                                Text(text = "BottomSheetWithCloseScreen")
                            }

                            Button(onClick = {
                                navController.navigate(
                                    Screen.BottomSheetWithParameters(
                                        "testId-123"
                                    )
                                )
                            }) {
                                Text(text = "BottomSheetWithParameters")
                            }
                            Button(onClick = {
                                navController.navigate(
                                    Screen.Zoom(
                                        "testId-Zoom"
                                    )
                                )
                            }) {
                                Text(text = "Zoom")
                            }
                            var showBottomSheet by remember {
                                mutableStateOf(false)
                            }
                            Button(onClick = { showBottomSheet = true }) {
                                Text(text = "BottomSheetOutsideNav")
                            }

                            if (showBottomSheet) {
                                ModalBottomSheet(
                                    onDismissRequest = {
                                        showBottomSheet = false
                                    },
                                    content = {
                                        Text(text = "ModalBottomSheet outside navigation")
                                    },
                                )
                            }

                        }

                    }
                    composable<Screen.Zoom> { backStackEntry ->
                        val id = backStackEntry.toRoute<Screen.Zoom>().id
                        Column {
                            Text(
                                text = "Zoom  param:$id",
                            )

                            Button(onClick = { navController.popBackStack() }) {
                                Text(text = "back")
                            }
                        }
                    }

                    bottomSheet<Screen.BottomSheetFullScreen> {
                        BSFullScreenLayout()
                    }

                    bottomSheet<Screen.BottomSheetWithCloseScreen> {
                        BSWithCloseLayout {
                            navController.popBackStack()
                        }
                    }
                    bottomSheet<Screen.BottomSheetWithParameters> { backStackEntry ->
                        val id =
                            backStackEntry.toRoute<Screen.BottomSheetWithParameters>().id
                        BSWithParametersLayout(id)
                    }
                }
            }
        }
    }
}
