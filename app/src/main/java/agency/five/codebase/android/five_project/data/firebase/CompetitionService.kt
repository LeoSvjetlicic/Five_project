package agency.five.codebase.android.five_project.data.firebase

import agency.five.codebase.android.five_project.data.firebase.model.DBCompetition
import agency.five.codebase.android.five_project.data.firebase.model.DBMember
import agency.five.codebase.android.five_project.data.firebase.model.DBTeam

interface CompetitionService {
    suspend fun fetchCompetitions(): List<DBCompetition>
    suspend fun fetchTeams(): List<DBTeam>
    suspend fun fetchMembers(): List<DBMember>
}
