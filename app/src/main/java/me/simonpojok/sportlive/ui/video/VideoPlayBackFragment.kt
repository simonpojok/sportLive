package me.simonpojok.sportlive.ui.video

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import me.simonpojok.sportlive.R
import me.simonpojok.sportlive.ui.common.fragments.BaseFragment
import me.simonpojok.sportlive.ui.common.viewmodel.DialogCommand
import javax.inject.Inject

@AndroidEntryPoint
class VideoPlayBackFragment : BaseFragment<VideoPlayBackViewState, DialogCommand>() {
    override val layout = R.layout.fragment_video_play_back
    private val videoPlayer: PlayerView get() = requireView().findViewById(R.id.player_view)

    private var player: ExoPlayer? = null

    @Inject
    override lateinit var destinationToNavigationMapper: VideoPlayBackUiDestinationToNavigationMapper
    override val viewModel: VideoPlayBackViewModel by viewModels()

    private val videoPlayBackArguments: VideoPlayBackFragmentArgs by navArgs()

    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.handleNewVideUrl(videoPlayBackArguments.videoUrl)

        player = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                videoPlayer.player = exoPlayer
                val mediaItem = MediaItem.fromUri(videoPlayBackArguments.videoUrl)
                exoPlayer.setMediaItem(mediaItem)
            }
    }

    override fun renderViewState(viewState: VideoPlayBackViewState) {
        super.renderViewState(viewState)
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                videoPlayer.player = exoPlayer
                val mediaItem = MediaItem.fromUri(viewModel.currentViewState().videoUrl)
                exoPlayer.setMediaItem(mediaItem)
            }
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
    }
}
