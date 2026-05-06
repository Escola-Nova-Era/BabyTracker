//
//  AppColors.swift
//  BabyTracker

import SwiftUI

// Catálogos central de cores do app

enum AppColors {
    
    // MARK: Fundo
    
    // Tons claros de fundo
    static let backgroundTop = Color(red: 255.0/255.0, green: 242.0/255.0, blue: 246.0/255.0)
    static let backgroundBottom = Color(red: 240.0/255.0, green: 248.0/255.0, blue: 255.0/255.0)
    
    // MARK: Superfícies
    
    // Fundo principal de cards e campos
    static let surface = Color.white.opacity(0.88)
    
    // Fundo mais sólido para componentes que precisam de maior contraste
    static let surfaceStrong = Color.white
    
    // Fundo neutro suave usado em cards secundários e blocos internos
    static let surfaceMuted = Color(red: 245.0/255.0, green: 247.0/255.0, blue: 251.0/255.0)
    
    // MARK: Cores de Identidade
    
    // Azul principal do app (Azul Bebê)
    static let primary = Color(red: 104.0/255, green: 196.0/255, blue: 241.0/255)
    
    // Azul suave usado como apoio visual e áreas mais leves
    static let primarySoft = Color(red: 143.0/255, green: 227.0/255, blue: 247.0/255)
    
    // Lilás principal de apoio, puxando para o degradê dos botões e cards
    static let accent = Color(red: 179.0/255, green: 157.0/255, blue: 255.0/255)
    
    // Rosa bebê suave usado como destaque quente complementar
    static let highlight = Color( red: 255.0/255, green: 222.0/255, blue: 232.0/255)
    
    // Verde suave para elementos positivos ou estados amigáveis
    static let mint = Color(red: 196.0/255, green: 239.0/255, blue: 220.0/255)
    
    // MARK: Variações suaves extras

    // Blue
    static let blueSoft = Color(red: 214.0/255, green: 238.0/255, blue: 250.0/255)
    static let blueMuted = Color(red: 186.0/255, green: 226.0/255, blue: 245.0/255)

    // Green
    static let greenSoft = Color(red: 220.0/255, green: 243.0/255, blue: 230.0/255)
    static let greenMuted = Color(red: 196.0/255, green: 230.0/255, blue: 208.0/255)

    // Purple
    static let purpleSoft = Color(red: 229.0/255, green: 220.0/255, blue: 250.0/255)
    static let purpleMuted = Color(red: 210.0/255, green: 196.0/255, blue: 242.0/255)

    // Orange
    static let orangeSoft = Color(red: 255.0/255, green: 232.0/255, blue: 214.0/255)
    static let orangeMuted = Color(red: 250.0/255, green: 214.0/255, blue: 181.0/255)

    // Yellow
    static let yellowSoft = Color(red: 255.0/255, green: 244.0/255, blue: 204.0/255)
    static let yellowMuted = Color(red: 250.0/255, green: 232.0/255, blue: 170.0/255)
    
    // MARK: Hierarquia de texto
    
    // Texto principal forte
    static let textPrimary = Color(red: 30.0/255, green: 30.0/255, blue: 30.0/255)
    
    // Texto secundário para subtítulos e descrições
    static let textSecondary = Color(red: 138.0/255, green: 138.0/255, blue: 138.0/255)
    
    // Texto de menor destaque, placeholders e apoio
    static let textTertiary = Color(red: 189.0/255, green: 189.0/255, blue: 189.0/255)
    
    // MARK: Bordas e Divisórias
    
    // Borda clara para campos e superfícies suaves
    static let border = Color.white.opacity(0.9)
    
    // Divisórias neutras
    static let divider = Color(red: 230.0/255, green: 235.0/255, blue: 242.0/255)
    
    // MARK: Estados
    
    // Cor de sucesso
    static let success = Color(red: 105.0/255, green: 189.0/255, blue: 145.0/255)
}


