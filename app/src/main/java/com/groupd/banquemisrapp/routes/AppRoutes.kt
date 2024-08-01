package com.groupd.banquemisrapp.routes

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.groupd.banquemisrapp.routes.Route.CHANGE_PASSWORD
import com.groupd.banquemisrapp.routes.Route.EDIT_PROFILE
import com.groupd.banquemisrapp.routes.Route.HOME
import com.groupd.banquemisrapp.routes.Route.PROFILE_INFO
import com.groupd.banquemisrapp.routes.Route.SETTINGS
import com.groupd.banquemisrapp.routes.Route.SIGNIN
import com.groupd.banquemisrapp.routes.Route.SIGNUP
import com.groupd.banquemisrapp.routes.Route.SIGNUP2
import com.groupd.banquemisrapp.ui.screens.profile.EditProfileScreen

import com.groupd.banquemisrapp.ui.screens.profile.ProfileInformationScreen
import com.groupd.banquemisrapp.ui.screens.profile.SettingsScreen
import com.groupd.banquemisrapp.ui.screens.signin.SignInScreen
import com.groupd.banquemisrapp.ui.screens.signup.SignUpFirst
import com.groupd.banquemisrapp.ui.screens.signup.SignUpSecond
import com.groupd.banquemisrapp.activities.OnBoardingScreen
import com.groupd.banquemisrapp.activities.SplashScreen
import com.groupd.banquemisrapp.routes.Route.CARDS
import com.groupd.banquemisrapp.routes.Route.FAVOURITES
import com.groupd.banquemisrapp.routes.Route.HOME_SCREEN
import com.groupd.banquemisrapp.routes.Route.INTERNET_ERROR
import com.groupd.banquemisrapp.routes.Route.MORE
import com.groupd.banquemisrapp.routes.Route.NOTIFICATIONS
import com.groupd.banquemisrapp.routes.Route.PROFILE
import com.groupd.banquemisrapp.routes.Route.SERVER_ERROR
import com.groupd.banquemisrapp.routes.Route.SPLASH
import com.groupd.banquemisrapp.routes.Route.TRANSACTIONS
import com.groupd.banquemisrapp.routes.Route.TRANSACTION_DETAILS
import com.groupd.banquemisrapp.routes.Route.TRANSFER
import com.groupd.banquemisrapp.ui.screens.errors.InternetConnectionErrorScreen
import com.groupd.banquemisrapp.ui.screens.errors.ServerErrorScreen
import com.groupd.banquemisrapp.ui.screens.favorites.FavouriteScreen
import com.groupd.banquemisrapp.ui.screens.main.HomeScreen
import com.groupd.banquemisrapp.ui.screens.main.MoreScreen
import com.groupd.banquemisrapp.ui.screens.main.MyCardsScreen
import com.groupd.banquemisrapp.ui.screens.main.TransactionDetailsScreen
import com.groupd.banquemisrapp.ui.screens.main.TransactionsScreen
import com.groupd.banquemisrapp.ui.screens.main.TransferScreen
import com.groupd.banquemisrapp.ui.screens.profile.NotificationScreen
import com.groupd.banquemisrapp.ui.screens.profile.PasswordChangeScreen
import com.groupd.banquemisrapp.ui.screens.profile.ProfileScreen



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
    const val SPLASH = "splash"
    const val MORE = "more"
    const val TRANSFER = "transfer"
    const val TRANSACTIONS = "transactions"
    const val TRANSACTION_DETAILS = "transaction_details"
    const val CARDS = "cards"
    const val HOME_SCREEN = "home_screen"
    const val INTERNET_ERROR = "internet_error"
    const val SERVER_ERROR = "server_error"
    const val FAVOURITES = "favourites"
    const val NOTIFICATIONS = "notifications"

}


@Composable
fun AppNavHost() {
    /*NavHost(navController = navController, startDestination = SPLASH) {
        composable(route = SPLASH) { SplashScreen(navController = navController) }
        composable(route = HOME) { OnBoardingScreen(navController = navController) }
        composable(route = SIGNUP) { SignUpFirst(navController = navController) }
        composable(route = SIGNUP2) { SignUpSecond(navController = navController) }
        composable(route = SIGNIN) { SignInScreen(navController = navController) }
    }*/

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = SPLASH) {

            composable(route = SPLASH) { SplashScreen(navController = navController) }
            composable(route = HOME) { OnBoardingScreen(navController = navController) }
            composable(route = SIGNUP) { SignUpFirst(navController = navController) }
            composable(route = SIGNUP2) { SignUpSecond(navController = navController) }
            composable(route = SIGNIN) { SignInScreen(navController = navController) }

        }
    }


@Composable
fun MainNavHost(navController :NavHostController , modifier: Modifier) {
    //val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME_SCREEN) {

        composable(route = PROFILE) { ProfileScreen(navController = navController ) }
        composable(route = PROFILE_INFO) { ProfileInformationScreen(navController = navController, modifier = modifier ) }
        composable(route = MORE) { MoreScreen(navController = navController, modifier = modifier ) }
        composable(route = SETTINGS) { SettingsScreen(navController = navController, modifier = modifier ) }
        composable(route = EDIT_PROFILE) { EditProfileScreen(navController = navController, modifier = modifier ) }
        composable(route = CHANGE_PASSWORD) { PasswordChangeScreen(navController = navController, modifier = modifier ) }
        composable(route = HOME_SCREEN) { HomeScreen(navController = navController, modifier = modifier ) }
        composable(route = TRANSFER) { TransferScreen(navController = navController, modifier = modifier ) }
        composable(route = TRANSACTIONS) { TransactionsScreen(navController = navController, modifier = modifier ) }
        composable(route = TRANSACTION_DETAILS) { TransactionDetailsScreen(navController = navController, modifier = modifier ) }
        composable(route = CARDS) { MyCardsScreen(navController = navController, modifier = modifier ) }
        composable(route = INTERNET_ERROR) { InternetConnectionErrorScreen(navController = navController, modifier = modifier ) }
        composable(route = SERVER_ERROR) { ServerErrorScreen(navController = navController, modifier = modifier ) }
        composable(route = FAVOURITES) { FavouriteScreen(navController= navController, modifier = modifier )}
        composable(route = NOTIFICATIONS) { NotificationScreen(navController= navController, modifier = modifier )}




    }
}

