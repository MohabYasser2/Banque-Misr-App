package com.groupd.banquemisrapp.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.groupd.banquemisrapp.SplashScreen
import com.groupd.banquemisrapp.routes.Route.CHANGE_PASSWORD
import com.groupd.banquemisrapp.routes.Route.EDIT_PROFILE
import com.groupd.banquemisrapp.routes.Route.HOME
import com.groupd.banquemisrapp.routes.Route.PROFILE
import com.groupd.banquemisrapp.routes.Route.PROFILE_INFO
import com.groupd.banquemisrapp.routes.Route.SETTINGS
import com.groupd.banquemisrapp.routes.Route.SIGNIN
import com.groupd.banquemisrapp.routes.Route.SIGNUP
import com.groupd.banquemisrapp.routes.Route.SIGNUP2
import com.groupd.banquemisrapp.ui.screens.profile.EditProfileScreen
import com.groupd.banquemisrapp.ui.screens.profile.PasswordChangeScreen
import com.groupd.banquemisrapp.ui.screens.profile.ProfileInformationScreen
import com.groupd.banquemisrapp.ui.screens.profile.ProfileScreen
import com.groupd.banquemisrapp.ui.screens.profile.SettingsScreen
import com.groupd.banquemisrapp.ui.screens.signin.SignInScreen
import com.groupd.banquemisrapp.ui.screens.signup.SignUpFirst
import com.groupd.banquemisrapp.ui.screens.signup.SignUpSecond
import com.groupd.banquemisrapp.ui.screens.startup.OnBoardingScreen


object Route {
    const val HOME = "onboarding"
    const val SIGNUP = "signup"
    const val SIGNUP2 = "signup2"
    const val SIGNIN = "signin"
    const val PROFILE = "profile"
    const val PROFILE_INFO = "profile_info"
    const val SETTINGS = "settings"
    const val EDIT_PROFILE = "edit_profile"
    const val CHANGE_PASSWORD = "change_password"
}


@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME) {
        composable(route = HOME) { OnBoardingScreen(navController = navController) }
        composable(route = SIGNUP) { SignUpFirst(navController = navController) }
        composable(route = SIGNUP2) { SignUpSecond(navController = navController) }
        composable(route = SIGNIN) { SignInScreen(navController = navController) }


       // composable(route = PROFILE) { ProfileScreen(navController = navController) }
        composable(route = PROFILE_INFO) { ProfileInformationScreen(navController = navController) }
        composable(route = SETTINGS) { SettingsScreen(navController = navController) }
        composable(route = EDIT_PROFILE) { EditProfileScreen(navController = navController) }
        composable(route = CHANGE_PASSWORD) { PasswordChangeScreen(navController = navController) }



    }

}