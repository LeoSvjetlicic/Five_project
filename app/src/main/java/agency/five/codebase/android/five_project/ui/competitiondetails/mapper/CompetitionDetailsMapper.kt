package agency.five.codebase.android.five_project.ui.competitiondetails.mapper

import agency.five.codebase.android.five_project.model.CompetitionDetails
import agency.five.codebase.android.five_project.ui.competitiondetails.CompetitionDetailsViewState

interface CompetitionDetailsMapper {
    fun toCompetitionDetailsViewState(competitionDetails: CompetitionDetails):CompetitionDetailsViewState
}
