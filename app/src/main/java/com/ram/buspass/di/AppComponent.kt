package com.ram.buspass.di


import com.ram.buspass.features.bookingTicket.domain.BookingDetailsRepository
import com.ram.buspass.features.bookingTicket.domain.BookingDetailsUseCase
import com.ram.buspass.features.chanagePassword.domain.ChangePasswordRepository
import com.ram.buspass.features.chanagePassword.domain.ChangePasswordUseCase
import com.ram.buspass.features.editProfile.domain.EditProfileRepository
import com.ram.buspass.features.editProfile.domain.EditProfileUseCase
import com.ram.buspass.features.locationView.domain.LocationViewRepository
import com.ram.buspass.features.locationView.domain.LocationViewUseCase
import com.ram.buspass.features.login.domain.LoginRepository
import com.ram.buspass.features.login.domain.LoginUseCase
import com.ram.buspass.features.passVerify.domain.PassVerifyRepository
import com.ram.buspass.features.passVerify.domain.PassVerifyUseCase
import com.ram.buspass.features.profile.domain.ProfileRepository
import com.ram.buspass.features.profile.domain.ProfileUseCase
import com.ram.buspass.features.register.domain.RegisterRepository
import com.ram.buspass.features.register.domain.RegisterUseCase
import com.ram.buspass.features.ticketBook.domain.TicketBookRepository
import com.ram.buspass.features.ticketBook.domain.TicketBookUseCase
import com.ram.buspass.features.updateBusLocation.domain.UpdateBusLocationRepository
import com.ram.buspass.features.updateBusLocation.domain.UpdateBusLocationUseCase
import com.ram.buspass.features.verifyTicket.domain.VerifyTicketRepository
import com.ram.buspass.features.verifyTicket.domain.VerifyTicketUseCase
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
    fun provideProfileUseCase(profileRepository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase(profileRepository)
    }

    @Provides
    @Singleton
    fun provideEditProfileUseCase(editProfileRepository: EditProfileRepository): EditProfileUseCase {
        return EditProfileUseCase(editProfileRepository)
    }

    @Provides
    @Singleton
    fun provideChangePasswordUseCase(changePasswordRepository: ChangePasswordRepository): ChangePasswordUseCase {
        return ChangePasswordUseCase(changePasswordRepository)
    }

    @Provides
    @Singleton
    fun provideTicketBookUseCase(ticketBookRepository: TicketBookRepository): TicketBookUseCase {
        return TicketBookUseCase(ticketBookRepository)
    }

    @Provides
    @Singleton
    fun provideBookingUseCase(bookingDetailsRepository: BookingDetailsRepository): BookingDetailsUseCase {
        return BookingDetailsUseCase(bookingDetailsRepository)
    }

    @Provides
    @Singleton
    fun provideBusLocationUseCase(updateBusLocationRepository: UpdateBusLocationRepository): UpdateBusLocationUseCase {
        return UpdateBusLocationUseCase(updateBusLocationRepository)
    }

    @Provides
    @Singleton
    fun provideVerifyTicketUseCase(verifyTicketRepository: VerifyTicketRepository): VerifyTicketUseCase {
        return VerifyTicketUseCase(verifyTicketRepository)
    }

    @Provides
    @Singleton
    fun provideVerifyPassUseCase(passVerifyRepository: PassVerifyRepository): PassVerifyUseCase {
        return PassVerifyUseCase(passVerifyRepository)
    }

    @Provides
    @Singleton
    fun provideLocationViewUseCase(locationViewRepository: LocationViewRepository): LocationViewUseCase {
        return LocationViewUseCase(locationViewRepository)
    }



}