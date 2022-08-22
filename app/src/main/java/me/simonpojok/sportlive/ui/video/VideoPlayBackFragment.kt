package me.simonpojok.sportlive.ui.video

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import me.simonpojok.sportlive.R
import me.simonpojok.sportlive.ui.common.fragments.BaseFragment
import me.simonpojok.sportlive.ui.common.viewmodel.DialogCommand
import javax.inject.Inject

@AndroidEntryPoint
class VideoPlayBackFragment : BaseFragment<VideoPlayBackViewState, DialogCommand>() {
    override val layout = R.layout.fragment_video_play_back

    private val player: ExoPlayer
        get() {
            TODO()
        }

    @Inject
    override lateinit var destinationToNavigationMapper: VideoPlayBackUiDestinationToNavigationMapper
    override val viewModel: VideoPlayBackViewModel by viewModels()

    private val videoPlayBackArguments: VideoPlayBackFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleNewVideUrl(videoPlayBackArguments.videoUrl)
    }

    override fun renderViewState(viewState: VideoPlayBackViewState) {
        super.renderViewState(viewState)
    }
}
