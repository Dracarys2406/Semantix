package com.alaric.data.mock

import com.alaric.data.local.GameDao
import com.alaric.data.local.GameEntity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DatabaseSeeder @Inject constructor(
    private val gameDao: GameDao
) {
    fun seedMockData() {
        CoroutineScope(Dispatchers.IO).launch {
            val mockGames = listOf(
                GameEntity(
                    id = 119171,
                    title = "Baldur's Gate III",
                    coverUrl = "https://images.igdb.com/igdb/image/upload/t_thumb/co670h.jpg",
                    prominentImageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/ar3n2r.jpg",
                    screenshots = listOf(
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc81fj.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc81fh.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc81ff.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc81fl.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc81fn.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc81fe.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sctm4z.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scxvxn.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scxvxo.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scxtb7.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scxtb8.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scxtb4.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scxtb5.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scxtb6.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scxvy0.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scxvy1.jpg"
                    ),
                    rating = 95.66414011393256,
                    summary = "An ancient evil has returned to Baldur's Gate, intent on devouring it from the inside out. The fate of Faerun lies in your hands. Alone, you may resist. But together, you can overcome.",
                    genres = listOf("Role-playing (RPG)", "Strategy", "Turn-based strategy (TBS)"),
                    platforms = listOf(
                        "Google Stadia",
                        "Xbox Series X|S",
                        "Linux",
                        "PC (Microsoft Windows)",
                        "PlayStation 5",
                        "Mac"
                    ),
                    releaseDate = "August 02, 2023",
                    isStoredInQueue = true
                ),
                GameEntity(
                    id = 141472,
                    title = "The Witcher 3: Wild Hunt + Dark Souls III",
                    coverUrl = "https://images.igdb.com/igdb/image/upload/t_thumb/co7zoz.jpg",
                    prominentImageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/scjsar.jpg",
                    screenshots = listOf(
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scjsar.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scjsas.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scjsat.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scjsau.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scjsav.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scjsaw.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scjsax.jpg"
                    ),
                    rating = null,
                    summary = "A bundle containing The Witcher 3: Wild Hunt and Dark Souls III.",
                    genres = listOf("Role-playing (RPG)", "Adventure"),
                    platforms = listOf("PlayStation 4", "Xbox One"),
                    releaseDate = "October 25, 2018",
                    isStoredInQueue = false
                ),
                GameEntity(
                    id = 325591,
                    title = "Elden Ring Nightreign",
                    coverUrl = "https://images.igdb.com/igdb/image/upload/t_thumb/co95gk.jpg",
                    prominentImageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/ar3njy.jpg",
                    screenshots = listOf(
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scus1u.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scus1v.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scus1w.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scus1x.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scus1r.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scus1s.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scus1t.jpg"
                    ),
                    rating = 79.0671060890365,
                    summary = "Elden Ring: Nightreign is a standalone adventure within the ELDEN RING universe, crafted to offer players a new gaming experience by reimagining the game’s core design.",
                    genres = listOf("Role-playing (RPG)", "Hack and slash/Beat 'em up"),
                    platforms = listOf("Xbox Series X|S", "PlayStation 4", "PC (Microsoft Windows)", "PlayStation 5", "Xbox One"),
                    releaseDate = "May 29, 2025",
                    isStoredInQueue = true
                ),
                GameEntity(
                    id = 141540,
                    title = "Disco Elysium: The Final Cut",
                    coverUrl = "https://images.igdb.com/igdb/image/upload/t_thumb/co2ve1.jpg",
                    prominentImageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/ar3m2p.jpg",
                    screenshots = listOf(
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc8v3m.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc8v3n.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc8v3o.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc8v3p.jpg"
                    ),
                    rating = 94.76771590187865,
                    summary = "Disco Elysium: The Final Cut is an enhanced edition of the original role-playing game developed and published by ZA/UM. It retains the core gameplay and narrative while adding full voice acting, new quests, additional endings, and other narrative revisions.",
                    genres = listOf("Role-playing (RPG)", "Adventure", "Indie"),
                    platforms = listOf("Google Stadia", "Xbox Series X|S", "PlayStation 4", "PC (Microsoft Windows)", "PlayStation 5", "Mac", "Xbox One", "Nintendo Switch"),
                    releaseDate = "March 29, 2021",
                    isStoredInQueue = false
                ),
                GameEntity(
                    id = 103337,
                    title = "Divinity: Original Sin II - Definitive Edition",
                    coverUrl = "https://images.igdb.com/igdb/image/upload/t_thumb/co1y5o.jpg",
                    prominentImageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/ar6jf.jpg",
                    screenshots = listOf(
                        "https://images.igdb.com/igdb/image/upload/t_1080p/d7gcockrzslh1bda6yuk.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/e0ts4le5cqn5eemb1c5q.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/yx6rqk8k141hpln2fomc.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/yfd17cwrtstguxst7tvq.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/xqqrpxrtdzp56lmkayeg.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/qari15nzrfhq4zatxwig.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/japcap9qpsq5uy8nua5b.jpg"
                    ),
                    rating = 90.17620399425432,
                    summary = "There can only be one God. The Divine is dead. The Void approaches. And the powers lying dormant within you are soon to awaken. The battle for Divinity has begun. Choose wisely and trust sparingly; darkness lurks within every heart. Master deep, tactical combat. Join up to 3 other players - but know that only one of you will have the chance to become a God, in multi-award winning RPG Divinity: Original Sin 2.",
                    genres = listOf("Role-playing (RPG)", "Strategy", "Adventure"),
                    platforms = listOf("Xbox Series X|S", "PlayStation 4", "Nintendo Switch 2", "PC (Microsoft Windows)", "iOS", "PlayStation 5", "Mac", "Xbox One", "Nintendo Switch"),
                    releaseDate = "August 30, 2018",
                    isStoredInQueue = true
                ),
                GameEntity(
                    id = 134222,
                    title = "Persona 5 Royal: Launch Edition",
                    coverUrl = "https://images.igdb.com/igdb/image/upload/t_thumb/co8h07.jpg",
                    prominentImageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/sc8zkq.jpg",
                    screenshots = listOf(
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc8zkq.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc8zkr.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/sc8zks.jpg"
                    ),
                    rating = null,
                    summary = "The Launch Edition includes the game in a limited edition Steelbook case and a dynamic PS4 theme download.",
                    genres = listOf("Role-playing (RPG)", "Adventure"),
                    platforms = listOf("Xbox Series X|S", "PlayStation 4", "PlayStation 5", "Nintendo Switch"),
                    releaseDate = "March 30, 2020",
                    isStoredInQueue = false
                ),
                GameEntity(
                    id = 140839,
                    title = "Mass Effect Legendary Edition",
                    coverUrl = "https://images.igdb.com/igdb/image/upload/t_thumb/co2k5h.jpg",
                    prominentImageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/ar4mdk.jpg",
                    screenshots = listOf(
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scab35.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scab36.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scab37.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scab38.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scab39.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scab3a.jpg",
                        "https://images.igdb.com/igdb/image/upload/t_1080p/scab3b.jpg"
                    ),
                    rating = 90.71783077288131,
                    summary = "One person is all that stands between humanity and the greatest threat it’s ever faced. Relive the legend of Commander Shepard in the highly acclaimed Mass Effect trilogy with the Mass Effect Legendary Edition. Includes single-player base content and over 40 DLCs from Mass Effect, Mass Effect 2, and Mass Effect 3 games, including promo weapons, armors and packs. Experience an amazingly rich and detailed universe where your decisions have profound consequences on the action and the outcome.",
                    genres = listOf("Shooter", "Role-playing (RPG)", "Strategy", "Adventure"),
                    platforms = listOf("Xbox Series X|S", "PlayStation 4", "PC (Microsoft Windows)", "PlayStation 5", "Xbox One"),
                    releaseDate = "May 13, 2021",
                    isStoredInQueue = true
                )
            )
            gameDao.upsertGames(mockGames)
        }
    }
}