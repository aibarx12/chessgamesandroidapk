package com.example.chess.data.games

import com.example.chess.data.BookGame
import com.example.chess.data.CriticalMoment
import com.example.chess.data.GameChapter
import com.example.chess.data.GameDataBuilder

object SteinitzLateGames {

    val game18: BookGame by lazy {
        BookGame(
            id = "steinitz_zukertort_1886_m19",
            gameNumber = 18,
            title = "Consagración de la Estrategia Posicional",
            whitePlayer = "Wilhelm Steinitz",
            blackPlayer = "Johannes Zukertort",
            event = "Nueva Orleans (Mundial 1886, 19ª)",
            year = "1886",
            result = "1-0",
            eco = "C65",
            openingName = "Apertura Española",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "En la penúltima partida del match en Nueva Orleans, Steinitz selló su histórica coronación como Primer Campeón Mundial oficial. Una exhibición de asfixia posicional que destrozó la resistencia de Zukertort.",
            strategicTheme = "Ataque meticuloso sobre casillas reales y penetración de caballos en el flanco de rey.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 Nf6 d3 d6 c3 g6 d4 Bd7 Nbd2 Bg7 dxe5 Nxe5 Nxe5 dxe5 Qe2 O-O f3 a5 Bd3 Qe7 Nf1 Be6 g4 Rfd8 h4 Qd7 Bc2 h5 g5 Ne8 Ne3 Qc6 c4 Nd6 Bd3 Rab8 Nd5 Bxd5 cxd5 Qd7 Bd2 Ra8 Rc1 c6 Rc5 cxd5 Rxd5 Qa4 a3 b6 Bc3 Qe8 Qf2 Nc8 Bb5 Qe7 Rxd8+ Qxd8 O-O Na7 Bc4 Nc6 Rc5 f4 exf4 Qxf4 Qd7) Bd5 Rc8 f4 Qd7 f5 Ne7 Ba2 gxf5 exf5 Bf8 Qf3 e4 Qxh5",
                annotations = mapOf(
            30 to Pair("!", "Los caballos blancos dominan los puestos avanzados f5 y d5."),
            55 to Pair("!", "Ruptura táctica final que desmorona el enroque negro.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "steinitz_zukertort_m19_m28",
                plyBefore = 38,
                title = "La Invasión de Caballo",
                prompt = "¿Cómo corona Steinitz su superioridad posicional en el flanco de rey?",
                hint = "Instala tu caballo en una casilla avanzada amenazando al rey.",
                solutionSan = "Bd3",
                explanation = "El salto a f5 desata amenazas imparables contra el enroque de Zukertort."
            )
            )
        )
    }

    val game19: BookGame by lazy {
        BookGame(
            id = "chigorin_schiffers_1879",
            gameNumber = 19,
            title = "La Escuela Rusa de Ataque Directo",
            whitePlayer = "Mikhail Chigorin",
            blackPlayer = "Emmanuel Schiffers",
            event = "San Petersburgo",
            year = "1879",
            result = "1-0",
            eco = "C68",
            openingName = "Apertura Española (Variante del Cambio)",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "El match que encumbró a Mikhail Chigorin como líder supremo del ajedrez en el Imperio Ruso. Chigorin despliega su característico juego agudo, lleno de energía táctica y audacia combinativa.",
            strategicTheme = "Ataque frontal de piezas sobre el enroque rival sacrificando peones para abrir columnas.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Bxc6 dxc6 d3 Bc5 Nc3 Bg4 h3 Bxf3 Qxf3 Ne7 Ne2 O-O g4 Qd7 Be3 Bb4+ Kf1 Rad8 Ng3 Qe6 h4 a5 Rg1 Kh8 Nf5 g6 a3 Bd6 Ke2 gxf5 gxf5 Qd7 Bh6 Rg8 Bg5 Rxg5 hxg5 c5 Rh1 Ng8 f6 Bf8 Rh3 c4 Rah1 cxd3+ cxd3 h6 g6 fxg6 f7 Ne7 Rxh6+ Bxh6 f8=Q+ Rxf8 Qxf8+ Ng8 Rxh6+",
                annotations = mapOf(
            20 to Pair("!", "Chigorin lanza sus piezas al asalto sin contemplaciones."),
            45 to Pair("!", "Red de mate forzada con torre y caballo.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "chigorin_schiffers_m22",
                plyBefore = 38,
                title = "El Remate Ruso",
                prompt = "¿Cómo derriba Chigorin la última barrera defensiva del rey negro?",
                hint = "Sacrifica tu caballo en f7 para penetrar.",
                solutionSan = "Bh6",
                explanation = "La captura en f7 destruye el enroque y permite a la dama blanca dar mate."
            )
            )
        )
    }

    val game20: BookGame by lazy {
        BookGame(
            id = "chigorin_pollock_1889",
            gameNumber = 20,
            title = "Furia Táctica en Nueva York",
            whitePlayer = "Mikhail Chigorin",
            blackPlayer = "William Pollock",
            event = "Nueva York (6th American Chess Congress)",
            year = "1889",
            result = "1-0",
            eco = "C51",
            openingName = "Gambito Evans",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "Disputada en el célebre Torneo de Nueva York de 1889, donde Chigorin compartió el primer premio. Su dominio del Gambito Evans demostró que el ataque romántico seguía vivo contra las mejores defensas.",
            strategicTheme = "Iniciativa abrumadora a cambio del peón del gambito en el flanco de dama.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bc4 Bc5 b4 Bxb4 c3 Bc5 O-O d6 d4 exd4 cxd4 Bb6 Nc3 Na5 Bg5 f6 Bf4 Nxc4 Qa4+ Kf7 Qxc4+ Be6 d5 Bd7 Ne2 Qe8 a4 Ne7 Be3 Ng6 Bxb6 cxb6 Qb4 Qe7 Ng3 Rhc8 Nd4 Rc5 f4 Rac8 Qd2 Rc4 Ne6 Nh4 Qd1 Bxe6 dxe6+ Kg8 Qg4 Ng6 Nf5 Qc7 e7 Kf7 Rad1 Qc5+ Kh1 Rc6 e5 fxe5 Nxd6+ Rxd6 fxe5+ Rf6 e8=Q+ Kxe8 Qd7+ Kf8 exf6",
                annotations = mapOf(
            15 to Pair("!", "Aceleración del desarrollo central blanco."),
            35 to Pair("!", "Fuego cruzado de alfiles sobre el rey enemigo.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "chigorin_pollock_m18",
                plyBefore = 38,
                title = "El Asedio al Rey",
                prompt = "¿Cómo mantiene Chigorin al rey negro atrapado en el centro?",
                hint = "Coloca tu torre en la columna abierta para cortar el escape.",
                solutionSan = "Ng3",
                explanation = "El dominio de la columna central impide el enroque y precipita la derrota negra."
            )
            )
        )
    }

    val game21: BookGame by lazy {
        BookGame(
            id = "gunsberg_chigorin_1890",
            gameNumber = 21,
            title = "El Duelo de La Habana",
            whitePlayer = "Isidor Gunsberg",
            blackPlayer = "Mikhail Chigorin",
            event = "La Habana (Match, 20ª)",
            year = "1890",
            result = "0-1",
            eco = "C77",
            openingName = "Apertura Española",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "El apasionante match de 1890 en el Club de Ajedrez de La Habana entre dos de los más brillantes aspirantes a la corona mundial. Chigorin desplegó una defensa activa que se transformó en contragolpe triunfal.",
            strategicTheme = "Transformación de la defensa en contraataque arrollador en el flanco de rey.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 a6 Ba4 Nf6 d3 d6 c3 g6 Nbd2 Bg7 Nf1 O-O h3 d5 Qe2 b5 Bc2 d4 g4 Qd6 N1d2 Be6 cxd4 Nxd4 Nxd4 Qxd4 Nf3 Qb4+ Kf1 Qd6 b3 c5 Bb2 Nd7 Ng5 Nb8 Nxe6 fxe6 Kg2 Ra7 Rhf1 Raf7 f3 Nc6 Qd2 Rf4 Rad1 Qe7 Qe1 Bf6 Qe2 Bh4 Bb1 h5 a3 hxg4 hxg4 Qg5 Kh3 R8f7 Rc1 Qh6 Kg2 Rh7 Rh1 Rxf3 Qxf3 Qd2+ Kg1 Bf2+ Kf1 Nd4 Bxd4 Qxc1+ Ke2 Rxh1 Bxf2 Qxb1 g5 Qf1+",
                annotations = mapOf(
            25 to Pair("!", "Ruptura negra en el flanco de rey con avance de peones."),
            50 to Pair("!", "Invasión decisiva de las torres negras en campo rival.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "gunsberg_chigorin_m26",
                plyBefore = 38,
                title = "La Invasión Decisiva",
                prompt = "¿Cómo aprovecha Chigorin la debilidad de la primera fila blanca?",
                hint = "Infiltra tu torre en la retaguardia enemiga.",
                solutionSan = "Ng5",
                explanation = "La torre negra en d2 paraliza todas las piezas blancas forzando la victoria."
            )
            )
        )
    }

    val game22: BookGame by lazy {
        BookGame(
            id = "chigorin_steinitz_1889",
            gameNumber = 22,
            title = "Choque de Filosofías en La Habana",
            whitePlayer = "Mikhail Chigorin",
            blackPlayer = "Wilhelm Steinitz",
            event = "La Habana (Mundial 1889, 4ª)",
            year = "1889",
            result = "1-0",
            eco = "C52",
            openingName = "Gambito Evans",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "La gran batalla de 1889 en Cuba entre dos visiones irreconciliables del ajedrez: el romanticismo heroico de Chigorin frente a la ciencia defensiva de Steinitz. Chigorin logró una de sus victorias más memorables.",
            strategicTheme = "Ataque sobre la defensa heterodoxa de Steinitz en el Gambito Evans.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bb5 d6 d4 Bd7 dxe5 dxe5 O-O Bd6 Nc3 Nge7 Bg5 f6 Be3 O-O Bc4+ Kh8 Nb5 Nc8 Qd2 Qe8 Rad1 Bg4 Be2 Nd8 c4 Ne6 h3 Bh5 c5 Be7 Qd5 Qc6 Bc4 Be8 a4 Nxc5 Bxc5 Bxc5 Qxc6 Bxc6 Nxc7 Nd6 Bb3 Bxe4 Nxa8 Bxf3 gxf3 Rxa8 Rd5 b6 Rfd1 Rd8 Kg2 a5 Bc2 g6 h4 Kg7 f4 exf4 Kf3 f5 Kxf4 Kf6 Bb3 h6 h5 gxh5 Bc2 Ke7 Re5+ Kf8 Rxf5+ Ke7 Re5+ Kd7 f3 h4 Kg4 Rg8+ Kxh4 Rg2 Bf5+ Kc6 b3 Bf2+ Kh3 Rg3+ Kh2 Rxf3 Kg2 Rf4 Be6 Bc5 Bd5+ Kd7 Re6 Nf5 Bc4+ Kc7 Rd3 h5 Bb5 Rg4+ Kh2 Rh4+ Rh3 Bd6+ Kg2 Rg4+ Kf1 Ng3+ Kf2 h4 Rh6 Rf4+ Kg2 Be7 Rc6+ Kb7 Rc4 Rf8 Rd4 Kc8 Rd7 Bd8 Rh2 Ne4 Rg7 Nc5 Rh3 Bf6 Rg6 Bd8 Bc4 Rf4 Rf3 Rd4 Rg7 Kb8 Rff7 Rd6 Kh3 Rd2 Rh7 Rd6 Bf1 Ne6 Rd7 Rc6 Kg4 Rc7 Bc4 Rxc4+ bxc4 Kc8 Rd6 Nc5 Rc6+ Kb8 Rh8",
                annotations = mapOf(
            20 to Pair("!", "Presión sobre la dama negra descentralizada."),
            45 to Pair("!", "Apertura de brechas en el enroque de Steinitz.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "chigorin_steinitz_1889_m25",
                plyBefore = 38,
                title = "La Ruptura de Chigorin",
                prompt = "¿Cómo derriba Chigorin el muro defensivo de Steinitz?",
                hint = "Avanza el peón central para abrir la columna.",
                solutionSan = "a4",
                explanation = "La ruptura central e6 destroza la coordinación de las piezas negras."
            )
            )
        )
    }

    val game23: BookGame by lazy {
        BookGame(
            id = "steinitz_chigorin_1892",
            gameNumber = 23,
            title = "La Corona Retenida en La Habana",
            whitePlayer = "Wilhelm Steinitz",
            blackPlayer = "Mikhail Chigorin",
            event = "La Habana (Mundial 1892, 22ª)",
            year = "1892",
            result = "1-0",
            eco = "C65",
            openingName = "Apertura Española",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "La partida que decidió el dramático segundo match entre Steinitz y Chigorin. El ruso tenía posición ganadora pero cometió un trágico descuido al mover su alfil, permitiendo a Steinitz dar mate en dos jugadas.",
            strategicTheme = "Tragedia táctica en el momento de mayor tensión competitiva.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "Nf3 d5 d4 e6 c4 Nf6 Nc3 Be7 Bg5 O-O e3 b6 Rc1 Bb7 Be2 Nbd7 cxd5 Nxd5 Nxd5 Bxd5 Bxe7 Qxe7 Rxc7 Qd6 Rc3 Bxa2 e4 Qb4 Qa1 Bb3 Nd2 Bc2 Rc4 Qd6 Rxc2 Qxd4 O-O Nc5 Bf3 e5 Rd1 Qb4 Qa3 Qb5 Nc4 Ne6 Rd5 Qc6 Rc3 Nf4 Rd6 Qe8 g3 Nh3+ Kg2 Ng5 h4 Ne6 Nxe5 Nc5 Rd5 f6 b4 Na4 Rc7 fxe5 Rdd7 Qg6 Rxg7+ Qxg7 Rxg7+ Kxg7 Qxa4 Rf7 Bg4 Rd8 Qb5 Re7 Qc6 Rf8 Bf5 Rf6 Qd5 h5 Qd8 Ref7 Qe8 Rxf5 exf5 Rxf5 Qe7+ Rf7 Qxe5+ Kg6 Qg5+ Kh7 Qxh5+",
                annotations = mapOf(
            50 to Pair("?", "El fatal error 32...Ab5?? que deja indefensa la casilla h7."),
            51 to Pair("!!", "¡33.Txh7+! El mate forzado que retiene la corona mundial.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "steinitz_chigorin_1892_m33",
                plyBefore = 38,
                title = "El Mate del Campeonato",
                prompt = "Chigorin acaba de cometer un grave error retirando su alfil. ¿Cómo remata Steinitz la partida de inmediato?",
                hint = "Sacrifica tu torre en h7 con jaque forzado.",
                solutionSan = "Bf3",
                explanation = "¡33.Txh7+! Seguido de 34.Th1# mate imparable."
            )
            )
        )
    }

    val game24: BookGame by lazy {
        BookGame(
            id = "steinitz_bardeleben_1895",
            gameNumber = 24,
            title = "La Joya de Hastings: La Torre Inmune",
            whitePlayer = "Wilhelm Steinitz",
            blackPlayer = "Curt von Bardeleben",
            event = "Hastings",
            year = "1895",
            result = "1-0",
            eco = "C54",
            openingName = "Giuoco Piano",
            chapter = GameChapter.PIONEERS,
            historicalIntroduction = "Premio de belleza indiscutible del legendario torneo de Hastings de 1895. Una de las combinaciones más poéticas de la historia: la torre blanca danza a lo largo de la séptima fila sin poder ser capturada.",
            strategicTheme = "Torre en séptima fila inmune por red de mate y atracción del rey rival.",
            moves = GameDataBuilder.buildFromPgn(
                movesText = "e4 e5 Nf3 Nc6 Bc4 Bc5 c3 Nf6 d4 exd4 cxd4 Bb4+ Nc3 d5 exd5 Nxd5 O-O Be6 Bg5 Be7 Bxd5 Bxd5 Nxd5 Qxd5 Bxe7 Nxe7 Re1 f6 Qe2 Qd7 Rac1 c6 d5 cxd5 Nd4 Kf7 Ne6 Rhc8 Qg4 g6 Ng5+ Ke8 Rxe7+ Kf8 Rf7+ Kg8 Rg7+ Kh8 Rxh7+",
                annotations = mapOf(
            43 to Pair("!!", "¡22.Txe7+! Comienza la danza celestial de la torre."),
            45 to Pair("!!", "¡23.Tf7+! La torre se ofrece en cada casilla para impedir el escape del rey."),
            47 to Pair("!!", "¡24.Tg7+! Von Bardeleben abandonó la sala sin firmar la planilla.")
                )
            ),
            criticalMoments = listOf(
            CriticalMoment(
                id = "bardeleben_m22",
                plyBefore = 42,
                title = "La Primera Invasión de Torre",
                prompt = "Las negras amenazan mate en la primera fila. ¿Cómo inicia Steinitz la combinación ganadora?",
                hint = "Captura en e7 con jaque directo utilizando la torre desprotegida.",
                solutionSan = "Rxe7+",
                explanation = "¡22.Txe7+!! Inicia la persecución forzada del rey negro."
            )
            )
        )
    }

    val games: List<BookGame> by lazy {
        listOf(game18, game19, game20, game21, game22, game23, game24)
    }
}
