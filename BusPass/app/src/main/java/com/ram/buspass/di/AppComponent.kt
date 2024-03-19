package com.ram.buspass.di


import com.ram.buspass.features.login.domain.LoginRepository
import com.ram.buspass.features.login.domain.LoginUseCase
import com.ram.buspass.features.register.domain.RegisterRepository
import com.ram.buspass.features.register.domain.RegisterUseCase
import com.ram.buspass.features.ticketBook.domain.TicketBookRepository
import com.ram.buspass.features.ticketBook.domain.TicketBookUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppComponent {


    @Provides
    @Singleton
    fun provideRegisterUseCase(registerRepository: RegisterRepository): RegisterUseCase {
        return RegisterUseCase(registerRepository)
    }



    @Provides
    @Singleton
    fun provideLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCase(loginRepository)
    }

    @Provides
    @Singleton
    fun provideTicketBookUseCase(ticketBookRepository: TicketBookRepository): TicketBookUseCase {
        return TicketBookUseCase(ticketBookRepository)
    }
}