package agency.five.codebase.android.five_project.mock

import agency.five.codebase.android.five_project.model.*
import agency.five.codebase.android.five_project.ui.teamdetails.TeamDetailsRoute
import java.util.Collections.list

object Mock {
    fun getMembers() = List(21) {
        Member(
            id = it,
            name = "Luka Modrić",
            imageUrl = "https://pbs.twimg.com/profile_images/1501988258078674950/_5xMT_RA_400x400.jpg",
            isRightFooted = true,
            number = 10,
        )
    }

    fun getTeams() = List(17) {
        Team(
            id = 2,
            name = "Real Madrid 2017",
            imageUrl = "https://www.realmadrid.com/img/horizontal_940px/_5am0781_horizontal.jpg",
            position = 2,
            numberOfPoints = 27,
            description = "One of the greates football teams ever. \nThey won Club World Cup, LaLiga title, THE CHAMPIONS LEAGUE and UEFA & Spanish SuperCup"
        )
    }

    fun getTeamDetails(teamId:Int) = TeamDetails(
        team = getTeams()[0],
        members = getMembers(),
    )

    fun getCompetitionsList() = listOf(
        Competition(
            id = 1,
            isFollowed = false,
            name = "Laliga1",
            imageUrl = "https://a2.espncdn.com/combiner/i?img=%2Fi%2Fleaguelogos%2Fsoccer%2F500%2F15.png",
        ),
        Competition(
            id = 2,
            isFollowed = true,
            name = "Laliga2",
            imageUrl = "https://a2.espncdn.com/combiner/i?img=%2Fi%2Fleaguelogos%2Fsoccer%2F500%2F15.png",
        ),
        Competition(
            id = 3,
            isFollowed = false,
            name = "Laliga3",
            imageUrl = "https://a2.espncdn.com/combiner/i?img=%2Fi%2Fleaguelogos%2Fsoccer%2F500%2F15.png",
        ),
        Competition(
            id = 4,
            isFollowed = false,
            name = "Laliga4",
            imageUrl = "https://a2.espncdn.com/combiner/i?img=%2Fi%2Fleaguelogos%2Fsoccer%2F500%2F15.png",
        ),
        Competition(
            id = 5,
            isFollowed = true,
            name = "Laliga5",
            imageUrl = "https://a2.espncdn.com/combiner/i?img=%2Fi%2Fleaguelogos%2Fsoccer%2F500%2F15.png",
        ),
        Competition(
            id = 6,
            isFollowed = false,
            name = "Laliga6",
            imageUrl = "https://a2.espncdn.com/combiner/i?img=%2Fi%2Fleaguelogos%2Fsoccer%2F500%2F15.png",
        ),
        Competition(
            id = 7,
            isFollowed = false,
            name = "Laliga7",
            imageUrl = "https://a2.espncdn.com/combiner/i?img=%2Fi%2Fleaguelogos%2Fsoccer%2F500%2F15.png",
        ),
        Competition(
            id = 8,
            isFollowed = false,
            name = "Laliga8",
            imageUrl = "https://a2.espncdn.com/combiner/i?img=%2Fi%2Fleaguelogos%2Fsoccer%2F500%2F15.png",
        ),
        Competition(
            id = 9,
            isFollowed = true,
            name = "Laliga9",
            imageUrl = "https://a2.espncdn.com/combiner/i?img=%2Fi%2Fleaguelogos%2Fsoccer%2F500%2F15.png",
        ),
    )

    fun getCompetitionDetails(competitionId: Int) =
        CompetitionDetails(
            competition = getCompetitionsList()[competitionId],
            teams = getTeams(),
        )
}
