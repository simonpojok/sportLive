package me.simonpojok.sportlive.ui.video

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.simonpojok.sportlive.R
import me.simonpojok.sportlive.ui.common.fragments.BaseFragment
import me.simonpojok.sportlive.ui.common.viewmodel.DialogCommand
import javax.inject.Inject

@AndroidEntryPoint
class VideoPlayBackFragment : BaseFragment<VideoPlayBackViewState, DialogCommand>() {
    override val layout = R.layout.fragment_video_play_back

    @Inject
    override lateinit var destinationToNavigationMapper: VideoPlayBackUiDestinationToNavigationMapper
    override val viewModel: VideoPlayBackViewModel by viewModels()
}
