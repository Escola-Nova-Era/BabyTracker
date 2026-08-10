//
//  TipCard.swift
//  BabyTracker
//
//  Created by Ismael Costa on 30/07/26.
//

import SwiftUI

struct TipCard: View{
    let icon: String
    let title: String
    let description: String
    let iconColor: Color
    let backGroundIconColor: Color
    
    
    var body: some View {
        
        HStack(spacing: AppSpacing.medium){
            ZStack{
                Circle()
                    .fill(backGroundIconColor)
                    .frame(width: AppSpacing.xxxLarge, height: AppSpacing.xxxLarge)
                
                Image(systemName: icon)
                    .foregroundColor(iconColor)
                    .font(.system(size: AppSpacing.large))
                
            }
            VStack(alignment: .leading,spacing: AppSpacing.xxSmall){
                Text(title)
                    .font(AppTypography.subheadlineBold)
                    .foregroundColor(Color.black)
                Text(description)
                    .font(AppTypography.footnote)
                    .foregroundColor(.gray)
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            
        }
        .padding(.vertical, AppSpacing.small)
        .padding(.horizontal,AppSpacing.medium)
        .frame(maxWidth: .infinity)
        .background(AppColors.surface)
        .cornerRadius(AppTheme.cornerRadius)
        .padding(.horizontal, AppSpacing.xxSmall)
    }
}



#Preview {
    VStack(alignment: .leading,spacing: AppSpacing.medium){
        Text("Dicas do dia")
            .font(AppTypography.headline)
                .fontWeight(.bold)
                
        TipCard(icon: "moon.zzz.fill", title: "Sono tranquilo", description: "Mantenha o quarto escuro e silencioso para ajudar o bebe a dormir melhor", iconColor: AppColors.purpleSoft,backGroundIconColor: AppColors.purpleMuted)
        
        TipCard(icon: "drop.fill", title: "Hidratação", description: "Ofereça o peito com frequência nos primeiros meses.", iconColor: AppColors.blueSoft, backGroundIconColor:AppColors.blueMuted)
        
        TipCard(icon: "heart.fill", title: "Contato pela a pela", description: "Alguns minutos por dia fortalecem o vínculo e acalmam o bebê.", iconColor: AppColors.greenMuted, backGroundIconColor: AppColors.greenSoft)
        
        Spacer()
    }
    .padding()
    .background(AppColors.highlight)
}
