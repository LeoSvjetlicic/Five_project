package agency.five.codebase.android.five_project.ui.main

import agency.five.codebase.android.five_project.R
import agency.five.codebase.android.five_project.data.teamRepository.di.teamModule
import agency.five.codebase.android.five_project.navigation.*
import agency.five.codebase.android.five_project.ui.competitiondetails.CompetitionDetailsRoute
import agency.five.codebase.android.five_project.ui.competitiondetails.CompetitionDetailsViewModel
import agency.five.codebase.android.five_project.ui.followed.FollowedScreenRoute
import agency.five.codebase.android.five_project.ui.followed.FollowedScreenViewModel
import agency.five.codebase.android.five_project.ui.home.HomeScreenRoute
import agency.five.codebase.android.five_project.ui.home.HomeViewModel
import agency.five.codebase.android.five_project.ui.teamdetails.TeamDetailsRoute
import agency.five.codebase.android.five_project.ui.teamdetails.TeamDetailsViewModel
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.FirebaseApp
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val showBottomBar by remember {
        derivedStateOf {
            when (navBackStackEntry?.destination?.route) {
                CompetitionDetailsDestination.route -> false
                TeamDetailsDestination.route -> false
                else -> true
            }
        }
    }
    Scaffold(
        topBar = {
            TopBar(onImageClick = {
                navController.navigate(HOME_ROUTE) {
                    popUpTo(HOME_ROUTE) {
                        inclusive = true
                    }
                }
            })
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    destinations = listOf(
                        NavigationItem.HomeDestination,
                        NavigationItem.FollowedDestination
                    ),
                    onNavigateToDestination = {
                        navController.navigate(it.route) {
                            popUpTo(it.route) {
                                inclusive = true
                            }
                        }
                    },
                    currentDestination = navBackStackEntry?.destination
                )
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationItem.HomeDestination.route,
                modifier = Modifier.padding(padding)
            ) {
                composable(NavigationItem.HomeDestination.route) {
                    HomeScreenRoute(
                        onCompetitionCardClick = {
                            navController.navigate(
                                CompetitionDetailsDestination.createNavigationRoute(it)
                            )
                        },
                        homeViewModel = getViewModel()
                    )
                }
                composable(NavigationItem.FollowedDestination.route) {
                    FollowedScreenRoute(
                        onCompetitionCardClick = {
                            navController.navigate(
                                CompetitionDetailsDestination.createNavigationRoute(it)
                            )
                        },
                        followedScreenViewModel = getViewModel()
                    )
                }
                composable(
                    route = TeamDetailsDestination.route,
                    arguments = listOf(navArgument(TEAM_ID) { type = NavType.IntType })
                ) {
                    val teamId = it.arguments?.getInt(TEAM_ID)
                    val viewModel =
                        getViewModel<TeamDetailsViewModel>(parameters = {
                            parametersOf(teamId)
                        })
                    TeamDetailsRoute(viewModel)
                }
                composable(
                    route = CompetitionDetailsDestination.route,
                    arguments = listOf(navArgument(COMPETITION_ID) { type = NavType.IntType })
                ) {
                    val competitionId = it.arguments?.getInt(COMPETITION_ID)
                    val viewModel =
                        getViewModel<CompetitionDetailsViewModel>(parameters = {
                            parametersOf(competitionId)
                        })
                    CompetitionDetailsRoute(
                        onTeamCardClick = {
                            navController.navigate(
                                TeamDetailsDestination.createNavigationRoute(2)
                            )
                        },
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar(onImageClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.topbaricon),
        contentDescription = null,
        modifier = Modifier
            .height(65.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.dark_blue))
            .padding(5.dp)
            .clickable {
                onImageClick()
            },
        alignment = Alignment.Center
    )
}

@Composable
private fun BottomNavigationBar(
    destinations: List<NavigationItem>,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.dark_blue),
    ) {
        destinations.forEach { destination ->
            AddItem(
                destination = destination,
                onNavigateToDestination = { onNavigateToDestination(destination) },
                currentDestination = currentDestination
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    destination: NavigationItem,
    onNavigateToDestination: (NavigationItem) -> Unit,
    currentDestination: NavDestination?,
) {
    BottomNavigationItem(
        modifier = Modifier,
        label = {
            Text(
                text = stringResource(id = destination.labelId),
                fontSize = 15.sp,
                color = Color.White
            )
        },
        icon = {
            Image(
                modifier = Modifier.fillMaxHeight(0.3F),
                painter = painterResource(
                    id = if (currentDestination?.hierarchy?.any {
                            it.route == destination.route
                        } == true)
                        destination.selectedIconId
                    else
                        destination.unselectedIconId
                ),
                contentDescription = destination.labelId.toString(),
                contentScale = ContentScale.FillHeight
            )
        },
        selected = currentDestination?.route == destination.route,
        onClick = { onNavigateToDestination(destination) }
    )
}
