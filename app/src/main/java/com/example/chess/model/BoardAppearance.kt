package com.example.chess.model

import androidx.compose.ui.graphics.Color

/**
 * Familias de siluetas y formas de piezas, inspiradas en los sets vectoriales libres de Lichess.
 */
enum class PieceShapeFamily(
    val id: String,
    val displayName: String,
    val originDescription: String
) {
    CBURNETT(
        id = "cburnett",
        displayName = "Cburnett",
        originDescription = "Colin M.L. Burnett • Set estándar de Lichess con cruz nítida y crin dentada"
    ),
    MERIDA(
        id = "merida",
        displayName = "Mérida",
        originDescription = "Diseño clásico editorial de Lichess con caballo de cuello curvo y boca abierta"
    ),
    ALPHA(
        id = "alpha",
        displayName = "Alpha",
        originDescription = "Eric Bentzen • Siluetas geométricas contemporáneas, cortes limpios y nítidos"
    ),
    DUBROVNIK(
        id = "dubrovnik",
        displayName = "Dubrovnik 1950",
        originDescription = "Histórico juego de las Olimpiadas 1950 • Rey con esfera sin cruz y alfil con bola"
    ),
    CALIFORNIA(
        id = "california",
        displayName = "California",
        originDescription = "Estilo Staunton de torneo estadounidense con torres pesadas y corona real alta"
    ),
    PIROUETTI(
        id = "pirouetti",
        displayName = "Pirouetti",
        originDescription = "Diseño artístico de Lichess con curvas suaves, fluidas y silueta redondeada"
    )
}

/**
 * Estilos de tablero seleccionables por el usuario.
 */
enum class BoardTheme(
    val id: String,
    val displayName: String,
    val subtitle: String,
    val lightSquare: Color,
    val darkSquare: Color,
    val borderColor: Color,
    val accentColor: Color,
    val lastMoveColor: Color = Color(0x55F59E0B),
    val selectedSquareColor: Color = Color(0x99F6E05E),
    val checkColor: Color = Color(0x88EF4444),
    val bestMoveHighlight: Color = Color(0x6606B6D4)
) {
    CLASSIC_WALNUT(
        id = "classic_walnut",
        displayName = "Nogal de Torneo",
        subtitle = "Verde olivo y arce clásico (Estilo Lichess)",
        lightSquare = Color(0xFFEEEED2),
        darkSquare = Color(0xFF769656),
        borderColor = Color(0xFF2B2520),
        accentColor = Color(0xFFF59E0B),
        lastMoveColor = Color(0x55F59E0B),
        selectedSquareColor = Color(0x99F6E05E)
    ),
    WARM_WOOD(
        id = "warm_wood",
        displayName = "Madera Noble",
        subtitle = "Roble cálido y nogal miel tradicional",
        lightSquare = Color(0xFFF0D9B5),
        darkSquare = Color(0xFFB58863),
        borderColor = Color(0xFF422615),
        accentColor = Color(0xFFD97706),
        lastMoveColor = Color(0x55E67E22),
        selectedSquareColor = Color(0x99FCD34D)
    ),
    OCEAN_BLUE(
        id = "ocean_blue",
        displayName = "Azul Océano",
        subtitle = "Gris marino y azul pizarra relajante",
        lightSquare = Color(0xFFDEE3E6),
        darkSquare = Color(0xFF8CA2AD),
        borderColor = Color(0xFF1E293B),
        accentColor = Color(0xFF0284C7),
        lastMoveColor = Color(0x5538BDF8),
        selectedSquareColor = Color(0x9993C5FD)
    ),
    OBSIDIAN_DARK(
        id = "obsidian_dark",
        displayName = "Obsidiana & Carbón",
        subtitle = "Platino mate y grafito profundo nocturno",
        lightSquare = Color(0xFFB5B5B5),
        darkSquare = Color(0xFF4A4A4A),
        borderColor = Color(0xFF18181B),
        accentColor = Color(0xFF38BDF8),
        lastMoveColor = Color(0x5560A5FA),
        selectedSquareColor = Color(0x99FDE047)
    ),
    VINTAGE_PARCHMENT(
        id = "vintage_parchment",
        displayName = "Pergamino Vintage",
        subtitle = "Papiro tostado y sepia añejo de biblioteca",
        lightSquare = Color(0xFFF5EFE1),
        darkSquare = Color(0xFF9E7E5A),
        borderColor = Color(0xFF3B2A1A),
        accentColor = Color(0xFFCA8A04),
        lastMoveColor = Color(0x55D97706),
        selectedSquareColor = Color(0x99FDE68A)
    ),
    EMERALD_FOREST(
        id = "emerald_forest",
        displayName = "Esmeralda Profunda",
        subtitle = "Verde bosque y marfil de campeonato",
        lightSquare = Color(0xFFE5EDE0),
        darkSquare = Color(0xFF3E6B48),
        borderColor = Color(0xFF1C3822),
        accentColor = Color(0xFF10B981),
        lastMoveColor = Color(0x5534D399),
        selectedSquareColor = Color(0x99A7F3D0)
    ),
    ROYAL_AMETHYST(
        id = "royal_amethyst",
        displayName = "Amatista Imperial",
        subtitle = "Lavanda y púrpura real aterciopelado",
        lightSquare = Color(0xFFECE6F0),
        darkSquare = Color(0xFF7E57C2),
        borderColor = Color(0xFF311B92),
        accentColor = Color(0xFFEC4899),
        lastMoveColor = Color(0x55C084FC),
        selectedSquareColor = Color(0x99E9D5FF)
    ),
    ICE_CYAN(
        id = "ice_cyan",
        displayName = "Hielo Nórdico",
        subtitle = "Blanco escarcha y celeste polar ártico",
        lightSquare = Color(0xFFE0F2FE),
        darkSquare = Color(0xFF38BDF8),
        borderColor = Color(0xFF0369A1),
        accentColor = Color(0xFFF43F5E),
        lastMoveColor = Color(0x55BAE6FD),
        selectedSquareColor = Color(0x997DD3FC)
    );

    companion object {
        fun fromId(id: String?): BoardTheme {
            return entries.firstOrNull { it.id == id } ?: CLASSIC_WALNUT
        }
    }
}

/**
 * Estilos y formas para los juegos de piezas de ajedrez inspirados en Lichess.
 */
enum class PieceStyle(
    val id: String,
    val displayName: String,
    val subtitle: String,
    val shapeFamily: PieceShapeFamily,
    val whiteFill: Color,
    val whiteStroke: Color,
    val whiteDetail: Color,
    val whiteAccent: Color,
    val blackFill: Color,
    val blackStroke: Color,
    val blackDetail: Color,
    val blackAccent: Color
) {
    CLASSIC_STAUNTON(
        id = "classic_staunton",
        displayName = "Cburnett (Lichess Estándar)",
        subtitle = "El diseño oficial y más famoso de Lichess por Colin Burnett",
        shapeFamily = PieceShapeFamily.CBURNETT,
        whiteFill = Color(0xFFF9F7F1),
        whiteStroke = Color(0xFF262626),
        whiteDetail = Color(0xFF666666),
        whiteAccent = Color(0xFFD4AF37),
        blackFill = Color(0xFF262628),
        blackStroke = Color(0xFFE8E8E8),
        blackDetail = Color(0xFFAAAAAA),
        blackAccent = Color(0xFFE2E8F0)
    ),
    MERIDA(
        id = "merida",
        displayName = "Mérida (Editorial)",
        subtitle = "Siluetas de imprenta clásica con caballo de boca abierta",
        shapeFamily = PieceShapeFamily.MERIDA,
        whiteFill = Color(0xFFFFFDF8),
        whiteStroke = Color(0xFF1E293B),
        whiteDetail = Color(0xFF64748B),
        whiteAccent = Color(0xFF0284C7),
        blackFill = Color(0xFF1E293B),
        blackStroke = Color(0xFFF1F5F9),
        blackDetail = Color(0xFF94A3B8),
        blackAccent = Color(0xFF38BDF8)
    ),
    ALPHA(
        id = "alpha",
        displayName = "Alpha (Geométrico)",
        subtitle = "Líneas rectas vanguardistas de Eric Bentzen y alto contraste",
        shapeFamily = PieceShapeFamily.ALPHA,
        whiteFill = Color(0xFFFFFFFF),
        whiteStroke = Color(0xFF0F172A),
        whiteDetail = Color(0xFF94A3B8),
        whiteAccent = Color(0xFF38BDF8),
        blackFill = Color(0xFF0F172A),
        blackStroke = Color(0xFFFFFFFF),
        blackDetail = Color(0xFF64748B),
        blackAccent = Color(0xFF38BDF8)
    ),
    DUBROVNIK(
        id = "dubrovnik",
        displayName = "Dubrovnik 1950 (Fischer)",
        subtitle = "Set histórico de las Olimpiadas: Rey con esfera sin cruz",
        shapeFamily = PieceShapeFamily.DUBROVNIK,
        whiteFill = Color(0xFFF7F2E7),
        whiteStroke = Color(0xFF3C2F2F),
        whiteDetail = Color(0xFF8D6E63),
        whiteAccent = Color(0xFFD97706),
        blackFill = Color(0xFF2B201F),
        blackStroke = Color(0xFFE6D7CD),
        blackDetail = Color(0xFFBCAAA4),
        blackAccent = Color(0xFFFFB74D)
    ),
    CALIFORNIA(
        id = "california",
        displayName = "California Staunton",
        subtitle = "Formas robustas de torneo americano con corona alta",
        shapeFamily = PieceShapeFamily.CALIFORNIA,
        whiteFill = Color(0xFFFAFAFA),
        whiteStroke = Color(0xFF1A1A1A),
        whiteDetail = Color(0xFF555555),
        whiteAccent = Color(0xFFCA8A04),
        blackFill = Color(0xFF212121),
        blackStroke = Color(0xFFEEEEEE),
        blackDetail = Color(0xFFBDBDBD),
        blackAccent = Color(0xFFE0E0E0)
    ),
    PIROUETTI(
        id = "pirouetti",
        displayName = "Pirouetti Moderno",
        subtitle = "Elegantes contornos orgánicos redondeados y fluidos",
        shapeFamily = PieceShapeFamily.PIROUETTI,
        whiteFill = Color(0xFFF5F7FA),
        whiteStroke = Color(0xFF334155),
        whiteDetail = Color(0xFF64748B),
        whiteAccent = Color(0xFF8B5CF6),
        blackFill = Color(0xFF334155),
        blackStroke = Color(0xFFF8FAFC),
        blackDetail = Color(0xFF94A3B8),
        blackAccent = Color(0xFFA78BFA)
    ),
    WOOD_MAHOGANY(
        id = "wood_mahogany",
        displayName = "Madera Artesanal (Mérida)",
        subtitle = "Boj dorado cálido y ébano café noble con silueta Mérida",
        shapeFamily = PieceShapeFamily.MERIDA,
        whiteFill = Color(0xFFEED9B7),
        whiteStroke = Color(0xFF5D3A1A),
        whiteDetail = Color(0xFF8B5A2B),
        whiteAccent = Color(0xFFD97706),
        blackFill = Color(0xFF3E2723),
        blackStroke = Color(0xFFD7CCC8),
        blackDetail = Color(0xFFBCAAA4),
        blackAccent = Color(0xFFFFCC80)
    ),
    ROYAL_GOLD(
        id = "royal_gold",
        displayName = "Oro Imperial (Dubrovnik)",
        subtitle = "Marfil real con filigrana dorada y silueta Dubrovnik",
        shapeFamily = PieceShapeFamily.DUBROVNIK,
        whiteFill = Color(0xFFFFFDF5),
        whiteStroke = Color(0xFFB8860B),
        whiteDetail = Color(0xFFD4AF37),
        whiteAccent = Color(0xFFFFD700),
        blackFill = Color(0xFF1E232A),
        blackStroke = Color(0xFFE2E8F0),
        blackDetail = Color(0xFF94A3B8),
        blackAccent = Color(0xFFE0E7FF)
    ),
    MINIMAL_MODERN(
        id = "minimal_modern",
        displayName = "Neo Minimalista (Alpha)",
        subtitle = "Contraste máximo puro en blanco y negro con formas Alpha",
        shapeFamily = PieceShapeFamily.ALPHA,
        whiteFill = Color(0xFFFFFFFF),
        whiteStroke = Color(0xFF0F172A),
        whiteDetail = Color(0xFF94A3B8),
        whiteAccent = Color(0xFF38BDF8),
        blackFill = Color(0xFF0F172A),
        blackStroke = Color(0xFFFFFFFF),
        blackDetail = Color(0xFF64748B),
        blackAccent = Color(0xFF38BDF8)
    ),
    VINTAGE_INK(
        id = "vintage_ink",
        displayName = "Tinta & Grabado (Cburnett)",
        subtitle = "Estilo de imprenta clásica de pergamino con formas Cburnett",
        shapeFamily = PieceShapeFamily.CBURNETT,
        whiteFill = Color(0xFFFFFFF4),
        whiteStroke = Color(0xFF111111),
        whiteDetail = Color(0xFF444444),
        whiteAccent = Color(0xFF262626),
        blackFill = Color(0xFF111111),
        blackStroke = Color(0xFFFBF9F5),
        blackDetail = Color(0xFFCCCCCC),
        blackAccent = Color(0xFFE5E5E5)
    );

    companion object {
        fun fromId(id: String?): PieceStyle {
            return entries.firstOrNull { it.id == id } ?: CLASSIC_STAUNTON
        }
    }
}
